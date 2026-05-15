@file:OptIn(androidx.compose.foundation.layout.ExperimentalLayoutApi::class)

package com.arogyanidhi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.HealthAndSafety
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Blue = Color(0xFF1976D2)
private val Green = Color(0xFF4CAF50)
private val Ink = Color(0xFF212121)
private val Muted = Color(0xFF757575)
private val PaleBlue = Color(0xFFEAF3FF)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { ArogyaNidhiApp() }
    }
}

@Composable
fun ArogyaNidhiApp() {
    var screen by rememberSaveable { mutableStateOf(Screen.Home.name) }
    var selectedSchemeId by rememberSaveable { mutableStateOf<String?>(null) }
    val quiz = remember { mutableStateOf(QuizState()) }

    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = Blue,
            secondary = Green,
            background = Color.White,
            surface = Color.White,
            onPrimary = Color.White,
            onBackground = Ink,
            onSurface = Ink
        )
    ) {
        Surface(Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            AnimatedContent(targetState = screen, label = "screen") { current ->
                when (Screen.valueOf(current)) {
                    Screen.Home -> HomeScreen(onStart = { screen = Screen.Step1.name })
                    Screen.Step1 -> QuizStepScreen(
                        step = 1,
                        title = "Select Your State",
                        subtitle = "Choose the state where you reside",
                        quizState = quiz.value,
                        onBack = { screen = Screen.Home.name },
                        onNext = { screen = Screen.Step2.name }
                    ) { StateStep(quiz.value) { quiz.value = quiz.value.copy(selectedState = it) } }
                    Screen.Step2 -> QuizStepScreen(
                        step = 2,
                        title = "Annual Household Income",
                        subtitle = "Enter your total family income per year",
                        quizState = quiz.value,
                        onBack = { screen = Screen.Step1.name },
                        onNext = { screen = Screen.Step3.name }
                    ) { IncomeStep(quiz.value) { quiz.value = quiz.value.copy(annualIncome = it) } }
                    Screen.Step3 -> QuizStepScreen(
                        step = 3,
                        title = "Your Occupation",
                        subtitle = "Select the occupation closest to your family situation",
                        quizState = quiz.value,
                        onBack = { screen = Screen.Step2.name },
                        onNext = { screen = Screen.Step4.name }
                    ) { OccupationStep(quiz.value) { quiz.value = quiz.value.copy(occupation = it) } }
                    Screen.Step4 -> QuizStepScreen(
                        step = 4,
                        title = "BPL Card Status",
                        subtitle = "Tell us whether your family has a Below Poverty Line card",
                        quizState = quiz.value,
                        onBack = { screen = Screen.Step3.name },
                        onNext = { screen = Screen.Step5.name }
                    ) { BplStep(quiz.value) { quiz.value = quiz.value.copy(hasBplCard = it) } }
                    Screen.Step5 -> QuizStepScreen(
                        step = 5,
                        title = "Family Size",
                        subtitle = "Enter the total number of people in your household",
                        quizState = quiz.value,
                        onBack = { screen = Screen.Step4.name },
                        onNext = { screen = Screen.Results.name }
                    ) { FamilyStep(quiz.value) { quiz.value = quiz.value.copy(familySize = it) } }
                    Screen.Results -> ResultsScreen(
                        quizState = quiz.value,
                        onBack = { screen = Screen.Step5.name },
                        onDocuments = {
                            selectedSchemeId = it
                            screen = Screen.Documents.name
                        },
                        onHospitals = { screen = Screen.Hospitals.name }
                    )
                    Screen.Documents -> DocumentScreen(
                        schemeId = selectedSchemeId ?: Schemes.first().id,
                        onBack = { screen = Screen.Results.name }
                    )
                    Screen.Hospitals -> HospitalFinderScreen(onBack = { screen = Screen.Results.name })
                }
            }
        }
    }
}

@Composable
private fun HomeScreen(onStart: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(Modifier.height(16.dp))
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .background(PaleBlue, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.HealthAndSafety, contentDescription = null, tint = Blue, modifier = Modifier.size(92.dp))
            }
            Text("Arogya-Nidhi", fontSize = 32.sp, fontWeight = FontWeight.Bold, color = Blue)
            Text("Your Digital Health Counsellor", color = Muted)
        }
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            InfoCard {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Verified, contentDescription = null, tint = Green)
                    Spacer(Modifier.width(12.dp))
                    Text("Health is wealth. Know your rights, access your care.", fontWeight = FontWeight.SemiBold)
                }
            }
            Card(colors = CardDefaults.cardColors(containerColor = PaleBlue), shape = RoundedCornerShape(12.dp)) {
                Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text("How It Works", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                    Text("1. Answer simple questions about your family")
                    Text("2. Find schemes you're eligible for")
                    Text("3. Get document checklist")
                    Text("4. Locate nearest hospitals")
                }
            }
        }
        Button(
            onClick = onStart,
            modifier = Modifier.fillMaxWidth().height(56.dp),
            shape = RoundedCornerShape(28.dp),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp)
        ) {
            Text("Check My Eligibility", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun QuizStepScreen(
    step: Int,
    title: String,
    subtitle: String,
    quizState: QuizState,
    onBack: () -> Unit,
    onNext: () -> Unit,
    content: @Composable () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Step $step of 5") },
                navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back") } }
            )
        },
        bottomBar = {
            Button(
                onClick = onNext,
                enabled = quizState.isStepValid(step),
                modifier = Modifier.fillMaxWidth().padding(16.dp).height(56.dp),
                shape = RoundedCornerShape(28.dp)
            ) {
                Text(if (step == 5) "Find My Schemes" else "Next", fontWeight = FontWeight.Bold)
            }
        }
    ) { padding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            Stepper(step)
            Column {
                Text(title, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Text(subtitle, color = Muted)
            }
            content()
            InfoCard { Text(helperTextFor(step), color = Ink) }
            Spacer(Modifier.height(92.dp))
        }
    }
}

@Composable
private fun Stepper(step: Int) {
    val labels = listOf("State", "Income", "Work", "BPL", "Family")
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        LinearProgressIndicator(progress = { step / 5f }, modifier = Modifier.fillMaxWidth(), color = Blue)
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            labels.forEachIndexed { index, label ->
                val number = index + 1
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .background(if (number <= step) Blue else Color(0xFFE0E0E0), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        if (number < step) Icon(Icons.Default.Check, null, tint = Color.White, modifier = Modifier.size(18.dp))
                        else Text(number.toString(), color = if (number == step) Color.White else Muted, fontWeight = FontWeight.Bold)
                    }
                    Text(label, fontSize = 12.sp, color = if (number == step) Blue else Muted)
                }
            }
        }
    }
}

@Composable
private fun StateStep(state: QuizState, onChange: (String) -> Unit) {
    ChoiceGroup(
        options = IndianStates,
        selected = state.selectedState,
        onSelect = onChange
    )
}

@Composable
private fun IncomeStep(state: QuizState, onChange: (Long?) -> Unit) {
    var text by rememberSaveable(state.annualIncome) { mutableStateOf(state.annualIncome?.toString().orEmpty()) }
    OutlinedTextField(
        value = text,
        onValueChange = {
            text = it.filter(Char::isDigit).take(10)
            onChange(text.toLongOrNull())
        },
        modifier = Modifier.fillMaxWidth(),
        label = { Text("Annual Income (INR) *") },
        prefix = { Text("Rs ") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true
    )
    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        listOf(
            "Below Rs 1 Lakh" to 75000L,
            "Rs 1-3 Lakhs" to 200000L,
            "Rs 3-5 Lakhs" to 400000L,
            "Rs 5-10 Lakhs" to 750000L,
            "Above Rs 10 Lakhs" to 1200000L
        ).forEach { (label, value) ->
            AssistChip(onClick = {
                text = value.toString()
                onChange(value)
            }, label = { Text(label) })
        }
    }
}

@Composable
private fun OccupationStep(state: QuizState, onChange: (String) -> Unit) {
    ChoiceGroup(
        options = listOf("Farmer", "Daily Wage Worker", "Self Employed", "Private Employee", "Government Employee", "Homemaker", "Student", "Retired", "Unemployed"),
        selected = state.occupation,
        onSelect = onChange
    )
}

@Composable
private fun BplStep(state: QuizState, onChange: (Boolean) -> Unit) {
    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        FilterChip(selected = state.hasBplCard == true, onClick = { onChange(true) }, label = { Text("Yes, I have BPL card") })
        FilterChip(selected = state.hasBplCard == false, onClick = { onChange(false) }, label = { Text("No BPL card") })
    }
}

@Composable
private fun FamilyStep(state: QuizState, onChange: (Int?) -> Unit) {
    var text by rememberSaveable(state.familySize) { mutableStateOf(state.familySize?.toString().orEmpty()) }
    OutlinedTextField(
        value = text,
        onValueChange = {
            text = it.filter(Char::isDigit).take(2)
            onChange(text.toIntOrNull())
        },
        modifier = Modifier.fillMaxWidth(),
        label = { Text("Family Size *") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true
    )
}

@Composable
private fun ChoiceGroup(options: List<String>, selected: String?, onSelect: (String) -> Unit) {
    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        options.forEach { option ->
            FilterChip(selected = selected == option, onClick = { onSelect(option) }, label = { Text(option) })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ResultsScreen(
    quizState: QuizState,
    onBack: () -> Unit,
    onDocuments: (String) -> Unit,
    onHospitals: () -> Unit
) {
    val schemes = remember(quizState) { EligibilityEngine.calculate(quizState, Schemes) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Eligible Schemes") },
                navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back") } }
            )
        },
        bottomBar = {
            Button(onClick = onHospitals, modifier = Modifier.fillMaxWidth().padding(16.dp).height(56.dp), shape = RoundedCornerShape(28.dp)) {
                Icon(Icons.Default.LocalHospital, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("Find Hospitals")
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(padding),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Text("You are eligible for ${schemes.size} health schemes", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                Text("Based on your income, BPL status, occupation, and Karnataka scheme rules.", color = Muted)
            }
            if (schemes.isEmpty()) {
                item { InfoCard { Text("No schemes match your current profile. Try checking district hospital support or updating your details.") } }
            }
            items(schemes) { scheme ->
                SchemeCard(scheme = scheme, onDocuments = { onDocuments(scheme.id) })
            }
            item { Spacer(Modifier.height(80.dp)) }
        }
    }
}

@Composable
private fun SchemeCard(scheme: HealthScheme, onDocuments: () -> Unit) {
    Card(shape = RoundedCornerShape(8.dp), elevation = CardDefaults.cardElevation(2.dp)) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.HealthAndSafety, contentDescription = null, tint = Blue)
                Spacer(Modifier.width(10.dp))
                Column(Modifier.weight(1f)) {
                    Text(scheme.name, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Text(scheme.type, color = Green, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
                AssistChip(onClick = {}, label = { Text("ACTIVE") })
            }
            Text(scheme.description, color = Muted)
            FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                scheme.benefits.take(3).forEach { AssistChip(onClick = {}, label = { Text(it, maxLines = 1, overflow = TextOverflow.Ellipsis) }) }
            }
            Button(onClick = onDocuments, modifier = Modifier.fillMaxWidth()) {
                Icon(Icons.Default.Description, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("View Documents")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DocumentScreen(schemeId: String, onBack: () -> Unit) {
    val scheme = Schemes.first { it.id == schemeId }
    val checked = remember { mutableStateMapOf<String, Boolean>() }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Document Checklist") },
                navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back") } }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(padding),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                InfoCard {
                    Column {
                        Text(scheme.name, fontWeight = FontWeight.Bold)
                        Text(scheme.applicationProcess, color = Muted)
                    }
                }
            }
            items(scheme.documents) { doc ->
                Card(shape = RoundedCornerShape(8.dp)) {
                    Row(Modifier.fillMaxWidth().padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(checked = checked[doc.name] == true, onCheckedChange = { checked[doc.name] = it })
                        Column {
                            Text(doc.name, fontWeight = FontWeight.SemiBold)
                            Text(doc.whyNeeded, color = Muted)
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HospitalFinderScreen(onBack: () -> Unit) {
    var query by rememberSaveable { mutableStateOf("") }
    var district by rememberSaveable { mutableStateOf("All") }
    val districts = listOf("All") + Hospitals.map { it.district }.distinct().sorted()
    val filtered = Hospitals.filter {
        (district == "All" || it.district == district) &&
            (query.isBlank() || it.name.contains(query, true) || it.address.contains(query, true) || it.district.contains(query, true))
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Empanelled Hospitals") },
                navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back") } }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(padding),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                OutlinedTextField(
                    value = query,
                    onValueChange = { query = it },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                    label = { Text("Search hospitals by name or location") },
                    singleLine = true
                )
            }
            item {
                FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    districts.forEach { item ->
                        FilterChip(selected = district == item, onClick = { district = item }, label = { Text(item) })
                    }
                }
            }
            if (filtered.isEmpty()) {
                item { InfoCard { Text("No hospitals found") } }
            }
            items(filtered) { hospital -> HospitalCard(hospital) }
        }
    }
}

@Composable
private fun HospitalCard(hospital: Hospital) {
    Card(shape = RoundedCornerShape(8.dp), elevation = CardDefaults.cardElevation(2.dp)) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.LocalHospital, contentDescription = null, tint = Blue)
                Spacer(Modifier.width(10.dp))
                Column(Modifier.weight(1f)) {
                    Text(hospital.name, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Text("${hospital.district} | ${hospital.type}", color = Muted)
                }
            }
            Text(hospital.address)
            Text("Phone: ${hospital.phone}", color = Muted)
            FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                hospital.schemesAccepted.forEach { AssistChip(onClick = {}, label = { Text(it) }) }
            }
        }
    }
}

@Composable
private fun InfoCard(content: @Composable () -> Unit) {
    Card(colors = CardDefaults.cardColors(containerColor = Color(0xFFF6F8FA)), shape = RoundedCornerShape(12.dp)) {
        Box(Modifier.fillMaxWidth().padding(16.dp)) { content() }
    }
}

private fun helperTextFor(step: Int) = when (step) {
    1 -> "We use your state to show relevant health schemes."
    2 -> "Income helps determine eligibility for government health schemes designed for low-income families."
    3 -> "Some schemes give priority to farmers, informal workers, and rural families."
    4 -> "A BPL card can unlock additional scheme benefits, but it is not required for every scheme."
    else -> "Family size helps estimate benefits under family floater and per-person coverage schemes."
}

private enum class Screen { Home, Step1, Step2, Step3, Step4, Step5, Results, Documents, Hospitals }

private data class QuizState(
    val selectedState: String? = null,
    val annualIncome: Long? = null,
    val occupation: String? = null,
    val hasBplCard: Boolean? = null,
    val familySize: Int? = null
) {
    fun isStepValid(step: Int) = when (step) {
        1 -> selectedState != null
        2 -> annualIncome != null && annualIncome > 0
        3 -> occupation != null
        4 -> hasBplCard != null
        5 -> familySize != null && familySize > 0
        else -> false
    }
}

private data class HealthScheme(
    val id: String,
    val name: String,
    val type: String,
    val description: String,
    val benefits: List<String>,
    val maxIncome: Long?,
    val requiresBpl: Boolean,
    val states: List<String>,
    val occupations: List<String> = emptyList(),
    val documents: List<Document>,
    val applicationProcess: String
)

private data class Document(val name: String, val whyNeeded: String)

private data class Hospital(
    val name: String,
    val district: String,
    val address: String,
    val phone: String,
    val type: String,
    val schemesAccepted: List<String>
)

private object EligibilityEngine {
    fun calculate(quiz: QuizState, schemes: List<HealthScheme>): List<HealthScheme> {
        return schemes.filter { scheme ->
            val stateOk = scheme.states.isEmpty() || quiz.selectedState in scheme.states
            val incomeOk = scheme.maxIncome == null || (quiz.annualIncome ?: Long.MAX_VALUE) <= scheme.maxIncome
            val bplOk = !scheme.requiresBpl || quiz.hasBplCard == true
            val occupationOk = scheme.occupations.isEmpty() || quiz.occupation in scheme.occupations
            stateOk && incomeOk && bplOk && occupationOk
        }
    }
}

private val IndianStates = listOf(
    "Karnataka", "Tamil Nadu", "Kerala", "Maharashtra", "Andhra Pradesh", "Telangana",
    "Goa", "Gujarat", "Madhya Pradesh", "Rajasthan", "Uttar Pradesh", "Delhi"
)

private val StandardDocs = listOf(
    Document("Aadhaar Card", "Identity verification"),
    Document("Ration Card", "Family and residence verification"),
    Document("Income Certificate", "Verify income eligibility for schemes"),
    Document("Bank Passbook", "Benefit transfer and account validation"),
    Document("Recent Photograph", "Application form and hospital records")
)

private val BplDocs = StandardDocs + Document("BPL Card", "Eligibility for BPL-specific schemes")
private val FarmerDocs = BplDocs + Document("Farmer or Cooperative Membership Proof", "Required for occupation-based cooperative schemes")

private val Schemes = listOf(
    HealthScheme(
        id = "ab-pmjay",
        name = "Ayushman Bharat PM-JAY",
        type = "Central",
        description = "Health assurance cover for economically vulnerable families with cashless secondary and tertiary care.",
        benefits = listOf("Cashless care", "Hospitalization", "Diagnostics"),
        maxIncome = 300000,
        requiresBpl = false,
        states = emptyList(),
        documents = BplDocs,
        applicationProcess = "Apply at the nearest Common Service Centre or Ayushman Mitra desk at an empanelled hospital."
    ),
    HealthScheme(
        id = "kaps",
        name = "Karnataka Arogya Sanjeevani / KAPS",
        type = "State",
        description = "Karnataka health coverage for BPL and low-income vulnerable families through network hospitals.",
        benefits = listOf("Cashless treatment", "Network hospitals", "Family coverage"),
        maxIncome = 300000,
        requiresBpl = false,
        states = listOf("Karnataka"),
        documents = BplDocs,
        applicationProcess = "Visit a Suvarna Arogya Suraksha Trust support desk or an empanelled hospital."
    ),
    HealthScheme(
        id = "rajiv-aarogyashree",
        name = "Rajiv Aarogyashree Scheme",
        type = "State",
        description = "BPL-oriented tertiary care support with hospital packages and food support during treatment.",
        benefits = listOf("Tertiary care", "Surgery packages", "Food support"),
        maxIncome = 250000,
        requiresBpl = true,
        states = listOf("Karnataka"),
        documents = BplDocs,
        applicationProcess = "Register at an empanelled hospital with Aadhaar, ration card, and BPL proof."
    ),
    HealthScheme(
        id = "yashasvini",
        name = "Yashasvini Cooperative Farmers Health Care Scheme",
        type = "State",
        description = "Cooperative health care support for farmers and rural workers.",
        benefits = listOf("Surgical care", "Rural coverage", "Cooperative support"),
        maxIncome = 500000,
        requiresBpl = false,
        states = listOf("Karnataka"),
        occupations = listOf("Farmer", "Daily Wage Worker"),
        documents = FarmerDocs,
        applicationProcess = "Apply through the cooperative society or local health support office."
    ),
    HealthScheme(
        id = "jsy",
        name = "Janani Suraksha Yojana",
        type = "Central",
        description = "Safe motherhood support for low-income pregnant women and families.",
        benefits = listOf("Maternity support", "Cash assistance", "Institutional delivery"),
        maxIncome = 300000,
        requiresBpl = false,
        states = emptyList(),
        documents = StandardDocs + Document("Pregnancy Record", "Required for maternity benefit verification"),
        applicationProcess = "Contact the ASHA worker, PHC, or government hospital maternity desk."
    ),
    HealthScheme(
        id = "rbsk",
        name = "Rashtriya Bal Swasthya Karyakram",
        type = "Central",
        description = "Child health screening and early intervention support for families with children.",
        benefits = listOf("Child screening", "Early intervention", "Referral support"),
        maxIncome = null,
        requiresBpl = false,
        states = emptyList(),
        documents = StandardDocs + Document("Child Age Proof", "Confirms eligibility for child health services"),
        applicationProcess = "Ask at the nearest anganwadi, school health team, PHC, or district early intervention centre."
    )
)

private val Hospitals = listOf(
    Hospital("Bangalore Baptist Hospital", "Bengaluru Urban", "Bellary Road, Hebbal, Bengaluru", "080-22024700", "Private", listOf("Ayushman Bharat", "KAPS", "Rajiv Aarogyashree")),
    Hospital("Victoria Hospital (BMCRI)", "Bengaluru Urban", "Fort Road, Bengaluru", "080-26701150", "Government", listOf("Ayushman Bharat", "KAPS", "Yashasvini")),
    Hospital("St. John's Medical College Hospital", "Bengaluru Urban", "Sarjapur Road, Bengaluru", "080-49466000", "Teaching", listOf("Ayushman Bharat", "KAPS")),
    Hospital("Manipal Hospital", "Bengaluru Urban", "Old Airport Road, Bengaluru", "080-22221111", "Private", listOf("All major schemes")),
    Hospital("KIMS Hospital", "Hubballi-Dharwad", "Vidyanagar, Hubballi", "0836-2370057", "Teaching", listOf("Ayushman Bharat", "KAPS", "Rajiv Aarogyashree")),
    Hospital("JSS Hospital", "Mysuru", "MG Road, Mysuru", "0821-2335555", "Teaching", listOf("Ayushman Bharat", "KAPS")),
    Hospital("Apollo BGS Hospital", "Mysuru", "Adichunchanagiri Road, Mysuru", "0821-2568888", "Private", listOf("All major schemes")),
    Hospital("Mysore Medical College Hospital", "Mysuru", "Irwin Road, Mysuru", "0821-2520512", "Government", listOf("Ayushman Bharat", "KAPS", "Rajiv Aarogyashree")),
    Hospital("Kasturba Medical College Hospital", "Dakshina Kannada", "Attavar, Mangaluru", "0824-2444590", "Teaching", listOf("Ayushman Bharat", "KAPS")),
    Hospital("AJ Hospital", "Dakshina Kannada", "Kuntikana, Mangaluru", "0824-2225533", "Private", listOf("All major schemes")),
    Hospital("Yenepoya Medical College Hospital", "Dakshina Kannada", "Deralakatte, Mangaluru", "0824-2204668", "Teaching", listOf("Ayushman Bharat", "KAPS")),
    Hospital("KLE Hospital", "Belagavi", "Nehru Nagar, Belagavi", "0831-2473777", "Teaching", listOf("All major schemes")),
    Hospital("District Hospital Kalaburagi", "Kalaburagi", "Aiwan-E-Shahi Area, Kalaburagi", "08472-278611", "Government", listOf("Ayushman Bharat", "KAPS")),
    Hospital("Basaveshwara Teaching & General Hospital", "Kalaburagi", "Sedam Road, Kalaburagi", "08472-247751", "Teaching", listOf("All major schemes")),
    Hospital("VIMS Hospital", "Ballari", "Cantonment, Ballari", "08392-235201", "Teaching", listOf("Ayushman Bharat", "KAPS")),
    Hospital("Tumkur District Hospital", "Tumakuru", "BH Road, Tumakuru", "0816-2278200", "Government", listOf("All major schemes")),
    Hospital("McGann Hospital", "Shivamogga", "Sagar Road, Shivamogga", "08182-222960", "Teaching", listOf("Ayushman Bharat", "KAPS")),
    Hospital("District Hospital Udupi", "Udupi", "Ajjarkad, Udupi", "0820-2520555", "Government", listOf("All major schemes")),
    Hospital("District Hospital Hassan", "Hassan", "Krishnaraja Pura, Hassan", "08172-268999", "Government", listOf("Ayushman Bharat", "KAPS")),
    Hospital("Mandya Institute of Medical Sciences", "Mandya", "Bangalore-Mysore Road, Mandya", "08232-222086", "Teaching", listOf("All major schemes")),
    Hospital("District Hospital Chikkamagaluru", "Chikkamagaluru", "Jyothi Nagar, Chikkamagaluru", "08262-230400", "Government", listOf("Ayushman Bharat", "KAPS")),
    Hospital("District Hospital Chitradurga", "Chitradurga", "Turuvanur Road, Chitradurga", "08194-222027", "Government", listOf("Ayushman Bharat", "KAPS")),
    Hospital("District Hospital Davanagere", "Davanagere", "PJ Extension, Davanagere", "08192-250075", "Government", listOf("All major schemes")),
    Hospital("District Hospital Koppal", "Koppal", "Hospet Road, Koppal", "08539-221155", "Government", listOf("Ayushman Bharat", "KAPS")),
    Hospital("District Hospital Raichur", "Raichur", "Station Road, Raichur", "08532-226700", "Government", listOf("All major schemes")),
    Hospital("District Hospital Bidar", "Bidar", "Udgir Road, Bidar", "08482-226347", "Government", listOf("Ayushman Bharat", "KAPS")),
    Hospital("District Hospital Vijayapura", "Vijayapura", "BLDE Road, Vijayapura", "08352-250263", "Government", listOf("All major schemes")),
    Hospital("District Hospital Bagalkot", "Bagalkot", "Navanagar, Bagalkot", "08354-235360", "Government", listOf("Ayushman Bharat", "KAPS")),
    Hospital("District Hospital Gadag", "Gadag", "Mulgund Road, Gadag", "08372-238101", "Government", listOf("Ayushman Bharat", "KAPS")),
    Hospital("District Hospital Kodagu", "Kodagu", "Madikeri, Kodagu", "08272-220606", "Government", listOf("All major schemes"))
)
