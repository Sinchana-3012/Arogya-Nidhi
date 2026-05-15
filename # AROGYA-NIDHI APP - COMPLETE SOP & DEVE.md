# AROGYA-NIDHI APP - COMPLETE SOP & DEVELOPMENT GUIDE

## 📋 TABLE OF CONTENTS
1. Project Overview & Vision
2. Complete Feature Specification
3. Screen-by-Screen Prompts
4. Backend Architecture & Data Models
5. Frontend Implementation Guide
6. Mock API & Data Structure
7. Development Roadmap
8. Testing & Deployment Guide

---

## 1. PROJECT OVERVIEW & VISION

**App Name:** Arogya-Nidhi  
**Platform:** Android (Kotlin)  
**Target Users:** Rural and semi-urban families in Karnataka, India  
**Primary Goal:** Digital Health Counsellor for government health scheme eligibility checking

**Vision Statement:**
To eliminate the Information Barrier by providing transparent, accessible information on government health schemes, ensuring every eligible citizen can access the healthcare they deserve.

---

## 2. COMPLETE FEATURE SPECIFICATION

### 2.1 CORE MODULES

#### Module 1: Splash & Onboarding
- App logo display
- Motivational health quote
- Brief user instructions
- CTA button: "Check Eligibility"

#### Module 2: Eligibility Quiz (Stepper UI)
- 5-step progressive form
- Data collection for scheme matching
- Offline-first functionality
- Input validation

#### Module 3: Scheme Results Engine
- Decision tree-based matching
- Display eligible schemes
- Scheme benefit summaries
- Offline capability

#### Module 4: Document Checklist
- Per-scheme document requirements
- Interactive checkbox list
- Document descriptions
- Offline access

#### Module 5: Hospital Finder
- Karnataka hospitals database
- District-wise filtering
- Search functionality
- Contact information display

---

## 3. SCREEN-BY-SCREEN PROMPTS

### 🎯 SCREEN 1: SPLASH/HOME SCREEN

**Prompt for AI Code Generator:**

```
Create a Splash/Home Screen for Android app with the following specifications:

LAYOUT REQUIREMENTS:
- Full-screen centered layout
- App logo at top-center (200x200dp, placeholder for now)
- App name "Arogya-Nidhi" below logo (32sp, bold, primary color)
- Tagline: "Your Digital Health Counsellor" (16sp, gray)

CONTENT SECTION (centered):
- Inspirational health quote card with light background:
  * Quote: "Health is wealth. Know your rights, access your care."
  * Icon: Small health icon (heart/medical symbol)
  * Rounded corners (12dp radius)
  * Padding: 16dp

INSTRUCTION BOX:
- Light blue background card with:
  * Header: "How It Works" (18sp, semi-bold)
  * Step 1: "📝 Answer simple questions about your family"
  * Step 2: "🎯 Find schemes you're eligible for"
  * Step 3: "📄 Get document checklist"
  * Step 4: "🏥 Locate nearest hospitals"
  * Padding: 20dp, margin: 16dp

PRIMARY CTA BUTTON:
- Text: "Check My Eligibility"
- Full-width button (match_parent with 24dp horizontal margin)
- Height: 56dp
- Primary color background
- White text (16sp, bold)
- Elevated (8dp elevation)
- Rounded corners (28dp radius)
- Bottom margin: 32dp from screen bottom

NAVIGATION:
- On button click: Navigate to Quiz Step 1
- Implement smooth fade transition

TECHNICAL SPECS:
- Language: Kotlin
- Use ConstraintLayout for root
- Implement Material Design 3 components
- Add ripple effect on button
- Use ViewBinding
- Add fade-in animation on screen load (duration: 500ms)

COLOR SCHEME:
- Primary: #1976D2 (Blue)
- Secondary: #4CAF50 (Green)
- Background: #FFFFFF
- Text Primary: #212121
- Text Secondary: #757575
```

---

### 🎯 SCREEN 2: QUIZ STEP 1 - STATE SELECTION

**Prompt for AI Code Generator:**

```
Create Quiz Step 1 Screen (State Selection) with Stepper UI:

STEPPER INDICATOR:
- Horizontal stepper at top showing 5 steps
- Current step: 1/5 highlighted
- Progress indicator: 20% filled
- Steps labeled: State > Income > Occupation > BPL > Family
- Active step in primary color, inactive in gray

SCREEN HEADER:
- Title: "Select Your State" (24sp, bold)
- Subtitle: "Choose the state where you reside" (14sp, gray)
- Padding top: 16dp

MAIN CONTENT:
- Single selection dropdown/spinner:
  * Label: "State *" (required field indicator)
  * Default text: "Select your state"
  * Options: List of Indian states (focus on Karnataka for this version)
  * Full-width with 16dp horizontal margin
  * Height: 56dp
  * Outlined style with rounded corners (8dp)
  
STATE OPTIONS:
- Karnataka (primary focus)
- Tamil Nadu
- Kerala
- Maharashtra
- Andhra Pradesh
- Telangana
- [Add other states]

VALIDATION:
- Show error if user tries to proceed without selection
- Error message: "Please select your state to continue"
- Red color for error text

INFO CARD (optional):
- Small info icon with tooltip
- Text: "We use your state to show relevant health schemes"
- Light background, 12dp padding

NAVIGATION BUTTONS:
- Bottom button container (fixed to bottom):
  * "Next" button (primary, enabled only when state selected)
  * Full-width, 56dp height, 16dp margin
  * Ripple effect on click

TECHNICAL IMPLEMENTATION:
- Fragment-based architecture
- Use MaterialAutoCompleteTextView for state dropdown
- Store selection in ViewModel (StateFlow)
- Navigation: Use Navigation Component
- Transition: Slide left animation to next step
- Save state in local storage (Room Database or SharedPreferences)

DATA MODEL:
data class QuizState(
    var selectedState: String? = null,
    var annualIncome: Long? = null,
    var occupation: String? = null,
    var hasBPLCard: Boolean? = null,
    var familySize: Int? = null
)

ACCESSIBILITY:
- Content descriptions for screen readers
- Minimum touch target: 48dp
- High contrast for text
```

---

### 🎯 SCREEN 3: QUIZ STEP 2 - ANNUAL INCOME

**Prompt for AI Code Generator:**

```
Create Quiz Step 2 Screen (Annual Income Input):

STEPPER INDICATOR:
- Show step 2/5 active (40% progress)
- Previous step (State) marked as completed with checkmark
- Smooth transition animation

SCREEN HEADER:
- Title: "Annual Household Income" (24sp, bold)
- Subtitle: "Enter your total family income per year" (14sp, gray)

MAIN INPUT:
- Currency input field:
  * Label: "Annual Income (₹) *"
  * Hint text: "Enter amount in Rupees"
  * Input type: Number (decimal allowed)
  * Prefix: "₹" symbol
  * Full-width, 56dp height
  * Outlined text field style
  * Max length: 10 digits

INCOME RANGE HELPER (Quick Select):
- Optional quick selection chips:
  * "Below ₹1 Lakh"
  * "₹1-3 Lakhs"
  * "₹3-5 Lakhs"
  * "₹5-10 Lakhs"
  * "Above ₹10 Lakhs"
- Single selection chip group
- On chip select: Auto-fill representative value

INFO SECTION:
- Expandable card: "Why we ask this?"
  * Content: "Income helps determine eligibility for government health schemes. Many schemes are designed for low-income families."
  * Icon: Info icon
  * Collapsible design

VALIDATION RULES:
- Minimum value: ₹0
- Maximum value: ₹99,99,99,999
- Error messages:
  * Empty: "Please enter your annual income"
  * Invalid: "Please enter a valid amount"
- Show validation on field blur or Next click

NAVIGATION BUTTONS:
- Two-button layout at bottom:
  * "Back" button (outlined, secondary)
  * "Next" button (filled, primary)
  * Both 48% width with gap between
  * Fixed to bottom with 16dp margin

TECHNICAL SPECS:
- Use TextInputLayout with TextInputEditText
- Format number with thousand separators (Indian system: ₹1,00,000)
- Use InputFilter for numeric validation
- Store in ViewModel as Long (in paisa for precision if needed)
- LiveData/StateFlow for reactive updates
- Add TextWatcher for real-time formatting

DATA BINDING:
quizViewModel.updateIncome(incomeValue)

ANIMATIONS:
- Entry: Slide from right
- Exit: Slide to left (Next) or right (Back)
```

---

### 🎯 SCREEN 4: QUIZ STEP 3 - OCCUPATION

**Prompt for AI Code Generator:**

```
Create Quiz Step 3 Screen (Occupation Selection):

STEPPER INDICATOR:
- Step 3/5 active (60% progress)
- Steps 1-2 completed with checkmarks

SCREEN HEADER:
- Title: "Primary Occupation" (24sp, bold)
- Subtitle: "Select your main source of income" (14sp, gray)

MAIN CONTENT - RADIO GROUP:
- Single selection list with radio buttons:
  * Farmer
  * Agricultural Labour
  * Daily Wage Worker
  * Self-Employed
  * Government Employee
  * Private Sector Employee
  * Student
  * Unemployed
  * Retired
  * Other

UI DESIGN:
- Each option as a Material Card:
  * Radio button on left
  * Occupation label (16sp)
  * Small icon representing occupation (optional)
  * Full-width with 12dp vertical spacing
  * 8dp rounded corners
  * Elevation changes on selection (4dp)
  * Selected card: Light primary color background

CONDITIONAL INPUT:
- If "Other" selected:
  * Show text input field below
  * Label: "Please specify your occupation"
  * Required when Other is selected

ACCESSIBILITY FEATURES:
- Radio group with proper labeling
- Each card tappable (full area clickable, not just radio)
- Ripple effect on selection
- Clear visual feedback for selection

SPECIAL OCCUPATIONS (for scheme matching):
- Tag certain occupations for priority schemes:
  * BPL-relevant: Farmer, Agricultural Labour, Daily Wage
  * Regular employment: Government/Private Employee
  * Vulnerable: Unemployed, Student

NAVIGATION:
- "Back" and "Next" buttons
- Next enabled only when selection made
- Store occupation string in ViewModel

TECHNICAL IMPLEMENTATION:
- RecyclerView or RadioGroup with custom styling
- Single selection enforcement
- ViewModel update:
  quizViewModel.updateOccupation(occupation)

DATA MODEL:
enum class OccupationType {
    FARMER, AGRICULTURAL_LABOUR, DAILY_WAGE,
    SELF_EMPLOYED, GOVERNMENT_EMPLOYEE,
    PRIVATE_EMPLOYEE, STUDENT, UNEMPLOYED,
    RETIRED, OTHER
}

VALIDATION:
- Ensure selection before proceeding
- If Other: ensure text input is not empty
```

---

### 🎯 SCREEN 5: QUIZ STEP 4 - BPL CARD STATUS

**Prompt for AI Code Generator:**

```
Create Quiz Step 4 Screen (BPL Card Status):

STEPPER INDICATOR:
- Step 4/5 active (80% progress)
- Steps 1-3 completed

SCREEN HEADER:
- Title: "BPL Card Status" (24sp, bold)
- Subtitle: "Below Poverty Line Card" (14sp, gray)

MAIN QUESTION:
- Large, clear question text:
  "Do you have a BPL (Below Poverty Line) Card?"
  (18sp, centered or left-aligned)

SELECTION OPTIONS:
- Two large choice cards (50-50 split or stacked):
  
  CARD 1 - YES:
  * Icon: Checkmark or card icon
  * Text: "Yes, I have BPL Card"
  * Background: Light green when selected
  * Border: 2dp when selected
  
  CARD 2 - NO:
  * Icon: Cross or no-card icon
  * Text: "No, I don't have BPL Card"
  * Background: Light gray when selected
  * Border: 2dp when selected

- Single selection (toggle between cards)
- Height: 120dp each
- Rounded corners: 12dp
- Elevation: 2dp (6dp when selected)

INFORMATION SECTION:
- Expandable "What is BPL Card?" section:
  * Short explanation of BPL card
  * Benefits of having BPL card
  * How it affects scheme eligibility
  * Collapsible design with expand/collapse animation

CONDITIONAL HELP:
- If "No" selected, show small info:
  "Don't worry! You may still be eligible for several schemes based on your income and occupation."

ADDITIONAL OPTIONS (optional):
- Checkbox: "I'm not sure" 
  * If checked, system assumes "No" but flags for review

NAVIGATION:
- "Back" and "Next" buttons
- Next enabled only after selection

TECHNICAL SPECS:
- Two CardView elements with click listeners
- Toggle selection state
- Store boolean in ViewModel:
  quizViewModel.updateBPLStatus(hasBPL: Boolean)
  
VISUAL FEEDBACK:
- Smooth color transition on selection
- Haptic feedback on selection (light vibration)
- Icon animation on select

DATA STORAGE:
var hasBPLCard: Boolean? = null
```

---

### 🎯 SCREEN 6: QUIZ STEP 5 - FAMILY SIZE

**Prompt for AI Code Generator:**

```
Create Quiz Step 5 Screen (Family Size - Final Step):

STEPPER INDICATOR:
- Step 5/5 active (100% progress)
- All previous steps completed
- Visual indication this is final step

SCREEN HEADER:
- Title: "Family Size" (24sp, bold)
- Subtitle: "Number of family members" (14sp, gray)

MAIN INPUT OPTIONS:
Provide TWO input methods:

METHOD 1 - NUMBER PICKER:
- Visual counter with +/- buttons:
  * Large display number in center (48sp)
  * "-" button on left
  * "+" button on right
  * Range: 1 to 20
  * Default: 4
  * Buttons: 56dp x 56dp, circular, primary color

METHOD 2 - DIRECT INPUT:
- Text input field below picker:
  * Label: "Or enter manually"
  * Number input type
  * Max value: 50
  * Validation: minimum 1

FAMILY DEFINITION HELPER:
- Info card with icon:
  "Family includes: Spouse, children, dependent parents, and other dependents living with you"
  * Light blue background
  * Small info icon
  * 12dp padding, 8dp rounded corners

QUICK SELECT CHIPS (optional):
- Common family sizes:
  * "2 members"
  * "3-4 members"
  * "5-6 members"
  * "7+ members"

WHY WE ASK:
- Collapsible section:
  "Family size helps determine benefits under schemes that provide per-person coverage or family floater policies."

REVIEW SUMMARY CARD:
- Show summary of all collected data:
  ```
  Review Your Information:
  ✓ State: [Selected State]
  ✓ Annual Income: ₹[Amount]
  ✓ Occupation: [Occupation]
  ✓ BPL Card: [Yes/No]
  ✓ Family Size: [Number] members
  ```
  * Editable: Tap any field to go back to that step
  * Light background card
  * Edit icons next to each field

VALIDATION:
- Ensure family size >= 1
- Show error: "Family size must be at least 1"
- Prevent negative numbers

NAVIGATION BUTTONS:
- "Back" button (secondary)
- "Find My Schemes" button (primary, highlighted)
  * Larger text (18sp)
  * Icon: Search or checkmark
  * Full-width, 64dp height
  * Gradient or prominent color
  * Shows loading state when calculating

TECHNICAL IMPLEMENTATION:
- NumberPicker widget or custom +/- implementation
- Update ViewModel:
  quizViewModel.updateFamilySize(size: Int)
  
- On "Find My Schemes" click:
  * Validate all quiz data
  * Show loading indicator
  * Calculate eligible schemes (200ms delay for UX)
  * Navigate to Results screen
  * Pass QuizState to results

DATA MODEL UPDATE:
quizViewModel.calculateEligibility()

ANIMATION:
- Entry: Slide from right
- Exit to Results: Fade + slide up transition
- Celebration animation when all data complete (optional)
```

---

### 🎯 SCREEN 7: SCHEME RESULTS

**Prompt for AI Code Generator:**

```
Create Scheme Results Screen (Eligible Schemes Display):

TOP SECTION - SUCCESS HEADER:
- Celebration UI element:
  * Success icon (checkmark in circle) or animation
  * Heading: "Great News!" (28sp, bold, green)
  * Subtext: "You are eligible for [X] health schemes" (16sp)
  * Light green background, 16dp padding

FILTER/SORT OPTIONS:
- Horizontal chip group:
  * "All Schemes" (default selected)
  * "Central Govt"
  * "State Govt"
  * "High Coverage"
- Sort dropdown: "Sort by Coverage" / "Sort by Name"

RESULTS LIST - RECYCLERVIEW:
Display each eligible scheme as Material Card:

SCHEME CARD DESIGN:
┌─────────────────────────────────────┐
│ 🏥 [Scheme Name]              ACTIVE │
│                                       │
│ Coverage: Up to ₹[Amount]             │
│ Type: [Central/State]                 │
│                                       │
│ Benefits:                             │
│ • [Benefit 1]                         │
│ • [Benefit 2]                         │
│                                       │
│ [View Documents] [More Details →]    │
└─────────────────────────────────────┘

CARD SPECIFICATIONS:
- Elevation: 4dp
- Rounded corners: 12dp
- Padding: 16dp
- Margin: 12dp vertical, 16dp horizontal
- Ripple effect on tap

SCHEME CARD COMPONENTS:
1. Header Row:
   - Scheme icon (health/government icon)
   - Scheme name (18sp, bold)
   - Badge: "ACTIVE" (green, 10sp, rounded)

2. Coverage Amount:
   - Large prominent text: "₹5,00,000" (24sp, primary color)
   - Label: "Coverage per year" (12sp, gray)

3. Scheme Type Chip:
   - "Central Govt" / "Karnataka State" / "District"
   - Small chip with icon

4. Key Benefits (expandable):
   - Bullet list of top 3 benefits
   - "Show more" link if more benefits exist
   - Icons for benefit types (hospitalization, medicine, etc.)

5. Action Buttons:
   - "View Documents" (outlined button)
   - "More Details" (text button with arrow)

EMPTY STATE (if no schemes match):
- Illustration or icon
- Message: "No schemes match your current profile"
- Subtext: "Try updating your information or contact local health office"
- "Retake Quiz" button

SCHEMES TO INCLUDE (Karnataka-focused):

1. AYUSHMAN BHARAT (PM-JAY):
   - Coverage: ₹5,00,000 per family/year
   - Eligibility: BPL families, specific occupations
   - Type: Central Government
   - Benefits: Secondary + tertiary hospitalization, free medicines, diagnostics

2. KARNATAKA AROGYA SANJIVANI (KAPS):
   - Coverage: Up to ₹3,00,000
   - Eligibility: Karnataka residents, BPL/low income
   - Type: State Government
   - Benefits: Cashless treatment at empanelled hospitals

3. RAJIV AAROGYASHREE:
   - Coverage: Variable by procedure
   - Eligibility: BPL card holders
   - Type: State Government
   - Benefits: Tertiary care procedures

4. YASHASVINI HEALTH SCHEME:
   - Coverage: ₹2,00,000
   - Eligibility: Farmers, rural workers
   - Type: Cooperative
   - Benefits: Surgery coverage

5. JANANI SURAKSHA YOJANA:
   - Coverage: Cash benefits + free delivery
   - Eligibility: Pregnant women, BPL families
   - Type: Central Government

6. RASHTRIYA BAL SWASTHYA KARYAKRAM (RBSK):
   - Coverage: Free screening + treatment
   - Eligibility: Children 0-18 years
   - Type: Central Government

BOTTOM ACTION BAR:
- Floating Action Button or bottom bar with:
  * "View All Documents" (aggregate checklist)
  * "Find Hospitals" (navigate to hospital finder)

NAVIGATION:
- Tap scheme card: Show full scheme details (bottom sheet or new screen)
- "View Documents" button: Navigate to document checklist for that scheme
- "Find Hospitals": Navigate to hospital finder

TECHNICAL IMPLEMENTATION:
```kotlin
data class HealthScheme(
    val id: String,
    val name: String,
    val type: SchemeType, // CENTRAL, STATE, DISTRICT
    val coverageAmount: Long,
    val eligibilityCriteria: EligibilityCriteria,
    val benefits: List<String>,
    val requiredDocuments: List<Document>,
    val isActive: Boolean
)

enum class SchemeType {
    CENTRAL_GOVERNMENT,
    STATE_GOVERNMENT,
    COOPERATIVE,
    DISTRICT
}
```

ELIGIBILITY LOGIC (Decision Tree):
```kotlin
fun calculateEligibleSchemes(quizState: QuizState): List<HealthScheme> {
    val eligibleSchemes = mutableListOf<HealthScheme>()
    
    // Ayushman Bharat
    if (quizState.annualIncome < 100000 || quizState.hasBPLCard == true) {
        eligibleSchemes.add(AYUSHMAN_BHARAT)
    }
    
    // Karnataka schemes (state check)
    if (quizState.selectedState == "Karnataka") {
        if (quizState.annualIncome < 300000) {
            eligibleSchemes.add(KAPS)
        }
        if (quizState.hasBPLCard == true) {
            eligibleSchemes.add(RAJIV_AAROGYASHREE)
        }
        if (quizState.occupation in listOf("FARMER", "AGRICULTURAL_LABOUR")) {
            eligibleSchemes.add(YASHASVINI)
        }
    }
    
    // Universal schemes
    eligibleSchemes.add(RBSK) // for families with children
    
    return eligibleSchemes
}
```

ACCESSIBILITY:
- Announce number of schemes found
- Each card readable by screen reader
- Sufficient color contrast
```

---

### 🎯 SCREEN 8: DOCUMENT CHECKLIST

**Prompt for AI Code Generator:**

```
Create Document Checklist Screen:

SCREEN HEADER:
- Back button (navigate to results)
- Title: "Required Documents" (24sp, bold)
- Subtitle: "For [Scheme Name]" (16sp, gray)

SCHEME INFO CARD (top):
- Mini summary of selected scheme:
  * Scheme name
  * Coverage amount
  * Small icon

CHECKLIST HEADER:
- Instructional text:
  "Please gather these documents before applying:"
  * Info icon
  * Light background

DOCUMENT LIST - INTERACTIVE CHECKLIST:

Each document as checkable card:
┌─────────────────────────────────────┐
│ ☐ Aadhaar Card                    ! │
│                                     │
│ Original + 2 photocopies            │
│ For all family members              │
│                                     │
│ [?] Why needed                      │
└─────────────────────────────────────┘

DOCUMENT CARD COMPONENTS:
1. Checkbox (Material checkbox):
   - Tappable
   - Saves state locally
   - Visual feedback (checkmark animation)

2. Document Name (18sp, semi-bold):
   - Required: Red asterisk *
   - Optional: Gray "optional" badge

3. Requirements Details (14sp, gray):
   - Number of copies needed
   - Specific requirements (original/photocopy)
   - Validity requirements

4. Importance Indicator:
   - "!" icon for mandatory
   - "Optional" badge for non-mandatory

5. Help Icon/Button:
   - Expandable "Why needed" section
   - Explains purpose of document
   - Tips for obtaining if not available

COMMON DOCUMENTS TO INCLUDE:

MANDATORY:
1. ✓ Aadhaar Card
   - Original + 2 photocopies
   - For all family members
   - Why: Identity and address proof

2. ✓ Income Certificate
   - Issued within last 3 months
   - From Tahsildar office
   - Why: Verify income eligibility

3. ✓ Ration Card
   - Original + 1 photocopy
   - Should show family members
   - Why: Family composition proof

4. ✓ BPL Card (if applicable)
   - Original + 2 photocopies
   - Valid BPL card
   - Why: Eligibility criteria

5. ✓ Residence Proof
   - Electricity bill / Rent agreement
   - Within last 3 months
   - Why: State residence verification

6. ✓ Bank Account Details
   - Passbook first page copy
   - Active account
   - Why: Benefit transfer

7. ✓ Passport Size Photos
   - 4 photos of each family member
   - Recent (within 6 months)
   - Why: Application processing

CONDITIONAL DOCUMENTS:
8. Age Proof (for senior citizens)
   - Birth certificate / School certificate
   - For members above 60

9. Employment Certificate
   - For specific occupation-based schemes
   - From employer / Panchayat

10. Caste Certificate (if applicable)
    - For SC/ST/OBC categories

PROGRESS INDICATOR:
- Top of screen: "[X] of [Y] documents collected"
- Progress bar showing completion percentage
- Updates as user checks items

ACTION BUTTONS SECTION:

1. "Download Checklist" button:
   - Export as PDF
   - Share via WhatsApp/Email
   - Outlined button

2. "Where to Get These?" button:
   - Shows government office locations
   - Online application links where available
   - Phone numbers for helplines

3. "Apply for Scheme" button (primary):
   - Opens application guidance
   - Links to official portals
   - Full-width, prominent

TIPS SECTION (collapsible):
- "Application Tips" card:
  * Best time to apply
  * Common mistakes to avoid
  * Processing time expectations
  * Follow-up procedures

TECHNICAL IMPLEMENTATION:
```kotlin
data class Document(
    val id: String,
    val name: String,
    val isMandatory: Boolean,
    val description: String,
    val copies: String, // "Original + 2 photocopies"
    val validityRequirement: String?,
    val whyNeeded: String,
    val obtainFrom: String,
    val isChecked: Boolean = false
)

// ViewModel
class DocumentChecklistViewModel : ViewModel() {
    private val _documents = MutableStateFlow<List<Document>>(emptyList())
    val documents: StateFlow<List<Document>> = _documents
    
    private val _progress = MutableStateFlow(0)
    val progress: StateFlow<Int> = _progress
    
    fun toggleDocument(documentId: String) {
        // Update checked state
        // Recalculate progress
    }
    
    fun getCompletionPercentage(): Int {
        val total = documents.value.filter { it.isMandatory }.size
        val checked = documents.value.filter { it.isMandatory && it.isChecked }.size
        return (checked * 100) / total
    }
}
```

PERSISTENCE:
- Save checklist state in Room Database
- Allow users to return and track progress
- Sync across scheme selections

SHARING FEATURE:
- Generate shareable checklist:
```
SCHEME: Ayushman Bharat
REQUIRED DOCUMENTS:
☐ Aadhaar Card (Original + 2 copies)
☐ Income Certificate (within 3 months)
☐ Ration Card (Original + 1 copy)
...
```

ACCESSIBILITY:
- Checkbox labels properly associated
- Expand/collapse sections keyboard accessible
- Content descriptions for all interactive elements
```

---

### 🎯 SCREEN 9: HOSPITAL FINDER

**Prompt for AI Code Generator:**

```
Create Hospital Finder Screen with Karnataka hospitals database:

SCREEN HEADER:
- Title: "Empanelled Hospitals" (24sp, bold)
- Subtitle: "Find hospitals near you" (14sp, gray)

SEARCH & FILTER SECTION:

1. SEARCH BAR (top):
   - Full-width search input
   - Icon: Search magnifying glass
   - Hint: "Search hospitals by name or location"
   - Real-time search filtering
   - Clear button (X) when text entered

2. FILTER CHIPS (horizontal scroll):
   - "All Districts" (default selected)
   - "Bengaluru Urban"
   - "Mysuru"
   - "Mangaluru"
   - "Hubballi-Dharwad"
   - "Belagavi"
   - [All 31 Karnataka districts]
   - Single selection
   - Scroll indicator if more chips

3. SORT OPTIONS:
   - Dropdown or bottom sheet:
     * "Sort by Name (A-Z)"
     * "Sort by District"
     * "Sort by Scheme Coverage"

HOSPITAL LIST - RECYCLERVIEW:

Each hospital as detailed card:

┌─────────────────────────────────────┐
│ 🏥 [Hospital Name]              ⭐ │
│ [District Name]                     │
│                                     │
│ 📍 [Full Address]                   │
│ 📞 [Phone Number]                   │
│                                     │
│ Schemes Accepted:                   │
│ • Ayushman Bharat                   │
│ • Karnataka Arogya Sanjivani        │
│                                     │
│ [📞 Call] [🗺️ Directions] [ℹ️ Info]  │
└─────────────────────────────────────┘

HOSPITAL CARD SPECIFICATIONS:

1. Hospital Name (18sp, bold)
2. District Badge (chip with location icon)
3. Address (14sp, gray, max 2 lines)
4. Phone Number (clickable, 16sp, primary color)
5. Schemes Accepted:
   - List of applicable schemes
   - Colored badges for each scheme
6. Action Buttons:
   - Call: Direct dial intent
   - Directions: Open in Google Maps
   - Info: Full details bottom sheet

KARNATAKA HOSPITALS DATABASE:

Include comprehensive hospital list organized by district:

BENGALURU URBAN:
1. Bangalore Baptist Hospital
   - Address: Bellary Road, Hebbal, Bengaluru 560024
   - Phone: 080-2215 8000
   - Schemes: Ayushman Bharat, KAPS, Rajiv Aarogyashree

2. Victoria Hospital (BMCRI)
   - Address: Fort, Bengaluru 560002
   - Phone: 080-2670 6301
   - Schemes: Ayushman Bharat, KAPS, Yashasvini

3. St. John's Medical College Hospital
   - Address: Sarjapur Road, Bengaluru 560034
   - Phone: 080-4955 5555
   - Schemes: Ayushman Bharat, KAPS

4. Manipal Hospital
   - Address: Old Airport Road, Bengaluru 560017
   - Phone: 080-2502 4444
   - Schemes: All major schemes

5. Narayana Health City
   - Address: Bommasandra, Bengaluru 560099
   - Phone: 080-6969 2222
   - Schemes: Ayushman Bharat, KAPS, Rajiv Aarogyashree

MYSURU:
6. JSS Hospital
   - Address: M G Road, Mysuru 570004
   - Phone: 0821-254 8000
   - Schemes: Ayushman Bharat, KAPS

7. Apollo BGS Hospital
   - Address: Adichunchanagiri Road, Mysuru 570023
   - Phone: 0821-425 0000
   - Schemes: All major schemes

8. Mysore Medical College Hospital (MMCRI)
   - Address: Irwin Road, Mysuru 570001
   - Phone: 0821-252 1400
   - Schemes: Ayushman Bharat, KAPS, Rajiv Aarogyashree

MANGALURU (DAKSHINA KANNADA):
9. Kasturba Medical College Hospital
   - Address: Attavar, Mangaluru 575001
   - Phone: 0824-242 4822
   - Schemes: Ayushman Bharat, KAPS

10. AJ Hospital
    - Address: Kuntikana, Mangaluru 575004
    - Phone: 0824-242 5425
    - Schemes: All major schemes

11. Yenepoya Medical College Hospital
    - Address: Deralakatte, Mangaluru 575018
    - Phone: 0824-210 9321
    - Schemes: Ayushman Bharat, KAPS

HUBBALLI-DHARWAD:
12. Karnataka Institute of Medical Sciences (KIMS)
    - Address: Vidyanagar, Hubballi 580022
    - Phone: 0836-234 7777
    - Schemes: All major schemes

13. SDM College of Medical Sciences
    - Address: Sattur, Dharwad 580009
    - Phone: 0836-244 6666
    - Schemes: Ayushman Bharat, KAPS, Rajiv Aarogyashree

BELAGAVI:
14. KLE Hospital
    - Address: Nehru Nagar, Belagavi 590010
    - Phone: 0831-247 3777
    - Schemes: All major schemes

15. Belagavi Institute of Medical Sciences
    - Address: Dr. B. R. Ambedkar Road, Belagavi 590001
    - Phone: 0831-247 1364
    - Schemes: Ayushman Bharat, KAPS

KALABURAGI (GULBARGA):
16. Basaveshwara Teaching & General Hospital
    - Address: Sedam Road, Kalaburagi 585105
    - Phone: 08472-255555
    - Schemes: All major schemes

BALLARI (BELLARY):
17. VIMS Hospital
    - Address: Cantonment, Ballari 583104
    - Phone: 08392-264111
    - Schemes: Ayushman Bharat, KAPS

TUMAKURU:
18. Tumkur District Hospital
    - Address: B H Road, Tumakuru 572102
    - Phone: 0816-227 7777
    - Schemes: All major schemes

SHIVAMOGGA:
19. McGann Hospital
    - Address: Gopi Circle, Shivamogga 577201
    - Phone: 08182-278222
    - Schemes: Ayushman Bharat, KAPS

HASSAN:
20. Hassan Institute of Medical Sciences
    - Address: Alur Road, Hassan 573201
    - Phone: 08172-269213
    - Schemes: All major schemes

[Continue for all 31 districts of Karnataka...]

EMPTY STATE (no results):
- Search icon illustration
- "No hospitals found"
- "Try different search terms or filters"
- "Reset Filters" button

BOTTOM SHEET - HOSPITAL DETAILS:
When "Info" tapped, show full details:
- Hospital name & logo
- Full address with map thumbnail
- All contact numbers
- Email (if available)
- Schemes accepted with logos
- Facilities available (ICU, Emergency, etc.)
- Operating hours
- "Get Directions" button (full-width, primary)

TECHNICAL IMPLEMENTATION:

```kotlin
data class Hospital(
    val id: String,
    val name: String,
    val district: String,
    val address: String,
    val phone: String,
    val email: String?,
    val latitude: Double,
    val longitude: Double,
    val schemesAccepted: List<String>,
    val facilities: List<String>,
    val isGovernment: Boolean
)

class HospitalFinderViewModel : ViewModel() {
    private val _hospitals = MutableStateFlow<List<Hospital>>(emptyList())
    val hospitals: StateFlow<List<Hospital>> = _hospitals
    
    private val _selectedDistrict = MutableStateFlow("All Districts")
    val selectedDistrict: StateFlow<String> = _selectedDistrict
    
    fun searchHospitals(query: String) {
        _hospitals.value = repository.searchHospitals(query)
    }
    
    fun filterByDistrict(district: String) {
        _selectedDistrict.value = district
        _hospitals.value = repository.getHospitalsByDistrict(district)
    }
    
    fun getAllKarnatakaDistricts(): List<String> {
        return listOf(
            "Bagalkot", "Ballari", "Belagavi", "Bengaluru Rural",
            "Bengaluru Urban", "Bidar", "Chamarajanagar", "Chikballapur",
            "Chikkamagaluru", "Chitradurga", "Dakshina Kannada", "Davanagere",
            "Dharwad", "Gadag", "Hassan", "Haveri", "Kalaburagi", "Kodagu",
            "Kolar", "Koppal", "Mandya", "Mysuru", "Raichur", "Ramanagara",
            "Shivamogga", "Tumakuru", "Udupi", "Uttara Kannada",
            "Vijayapura", "Yadgir", "Vijayanagara"
        )
    }
}
```

INTERACTION FEATURES:

1. CALL FUNCTIONALITY:
```kotlin
fun callHospital(phoneNumber: String) {
    val intent = Intent(Intent.ACTION_DIAL).apply {
        data = Uri.parse("tel:$phoneNumber")
    }
    startActivity(intent)
}
```

2. DIRECTIONS:
```kotlin
fun openDirections(lat: Double, lng: Double) {
    val gmmIntentUri = Uri.parse("geo:$lat,$lng?q=$lat,$lng(Hospital)")
    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
    mapIntent.setPackage("com.google.android.apps.maps")
    startActivity(mapIntent)
}
```

3. SHARE HOSPITAL INFO:
```kotlin
fun shareHospitalInfo(hospital: Hospital) {
    val shareText = """
        Hospital: ${hospital.name}
        District: ${hospital.district}
        Address: ${hospital.address}
        Phone: ${hospital.phone}
        Schemes: ${hospital.schemesAccepted.joinToString(", ")}
    """.trimIndent()
    
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, shareText)
    }
    startActivity(Intent.createChooser(shareIntent, "Share Hospital Info"))
}
```

OFFLINE FUNCTIONALITY:
- Store all hospital data in Room Database
- Enable search without internet
- Cache data for offline access

ACCESSIBILITY:
- Each hospital card fully accessible
- Phone numbers clickable with proper labels
- Map buttons with descriptions
```

---

## 4. BACKEND ARCHITECTURE & DATA MODELS

### 4.1 PROJECT STRUCTURE

```
app/
├── data/
│   ├── local/
│   │   ├── database/
│   │   │   ├── AppDatabase.kt
│   │   │   ├── dao/
│   │   │   │   ├── SchemeDao.kt
│   │   │   │   ├── HospitalDao.kt
│   │   │   │   └── QuizStateDao.kt
│   │   │   └── entities/
│   │   │       ├── SchemeEntity.kt
│   │   │       ├── HospitalEntity.kt
│   │   │       └── QuizStateEntity.kt
│   │   └── json/
│   │       ├── schemes.json
│   │       ├── hospitals.json
│   │       └── documents.json
│   ├── repository/
│   │   ├── SchemeRepository.kt
│   │   ├── HospitalRepository.kt
│   │   └── QuizRepository.kt
│   └── models/
│       ├── QuizState.kt
│       ├── HealthScheme.kt
│       ├── Hospital.kt
│       └── Document.kt
├── domain/
│   ├── usecases/
│   │   ├── CalculateEligibilityUseCase.kt
│   │   ├── FilterHospitalsUseCase.kt
│   │   └── GetDocumentChecklistUseCase.kt
│   └── eligibility/
│       └── EligibilityEngine.kt
├── ui/
│   ├── splash/
│   │   └── SplashFragment.kt
│   ├── quiz/
│   │   ├── QuizViewModel.kt
│   │   ├── Step1StateFragment.kt
│   │   ├── Step2IncomeFragment.kt
│   │   ├── Step3OccupationFragment.kt
│   │   ├── Step4BPLFragment.kt
│   │   └── Step5FamilyFragment.kt
│   ├── results/
│   │   ├── ResultsFragment.kt
│   │   └── ResultsViewModel.kt
│   ├── documents/
│   │   ├── DocumentChecklistFragment.kt
│   │   └── DocumentViewModel.kt
│   └── hospitals/
│       ├── HospitalFinderFragment.kt
│       └── HospitalViewModel.kt
└── utils/
    ├── Constants.kt
    └── Extensions.kt
```

### 4.2 COMPLETE DATA MODELS

**Prompt for AI Code Generator:**

```
Create complete data models for Arogya-Nidhi app:

FILE: QuizState.kt
```kotlin
package com.arogyaniidhi.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quiz_states")
data class QuizState(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    var selectedState: String? = null,
    var annualIncome: Long? = null,
    var occupation: OccupationType? = null,
    var hasBPLCard: Boolean? = null,
    var familySize: Int? = null,
    val timestamp: Long = System.currentTimeMillis()
) {
    fun isComplete(): Boolean {
        return selectedState != null &&
               annualIncome != null &&
               occupation != null &&
               hasBPLCard != null &&
               familySize != null
    }
    
    fun getCompletionPercentage(): Int {
        var completed = 0
        if (selectedState != null) completed++
        if (annualIncome != null) completed++
        if (occupation != null) completed++
        if (hasBPLCard != null) completed++
        if (familySize != null) completed++
        return (completed * 100) / 5
    }
}

enum class OccupationType {
    FARMER,
    AGRICULTURAL_LABOUR,
    DAILY_WAGE_WORKER,
    SELF_EMPLOYED,
    GOVERNMENT_EMPLOYEE,
    PRIVATE_SECTOR_EMPLOYEE,
    STUDENT,
    UNEMPLOYED,
    RETIRED,
    OTHER;
    
    fun isVulnerable(): Boolean {
        return this in listOf(
            FARMER, AGRICULTURAL_LABOUR,
            DAILY_WAGE_WORKER, UNEMPLOYED
        )
    }
}
```

FILE: HealthScheme.kt
```kotlin
package com.arogyaniidhi.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "health_schemes")
@TypeConverters(Converters::class)
data class HealthScheme(
    @PrimaryKey
    val id: String,
    val name: String,
    val shortName: String,
    val type: SchemeType,
    val level: SchemeLevel,
    val coverageAmount: Long,
    val description: String,
    val benefits: List<String>,
    val eligibilityCriteria: EligibilityCriteria,
    val requiredDocuments: List<String>,
    val applicationProcess: String,
    val contactInfo: ContactInfo,
    val isActive: Boolean = true,
    val launchYear: Int,
    val websiteUrl: String?
)

enum class SchemeType {
    HEALTH_INSURANCE,
    MATERNITY_BENEFIT,
    CHILD_HEALTH,
    SENIOR_CITIZEN,
    EMERGENCY_CARE,
    PREVENTIVE_CARE
}

enum class SchemeLevel {
    CENTRAL_GOVERNMENT,
    STATE_GOVERNMENT,
    DISTRICT,
    COOPERATIVE
}

data class EligibilityCriteria(
    val minIncome: Long? = null,
    val maxIncome: Long? = null,
    val requiresBPL: Boolean = false,
    val applicableStates: List<String> = emptyList(),
    val applicableOccupations: List<OccupationType> = emptyList(),
    val minAge: Int? = null,
    val maxAge: Int? = null,
    val familySizeMin: Int? = null,
    val familySizeMax: Int? = null,
    val gender: Gender? = null,
    val specialConditions: List<String> = emptyList()
)

enum class Gender {
    MALE, FEMALE, OTHER, ALL
}

data class ContactInfo(
    val helplineNumber: String,
    val email: String?,
    val websiteUrl: String?,
    val officeAddress: String?
)
```

FILE: Hospital.kt
```kotlin
package com.arogyaniidhi.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "hospitals")
@TypeConverters(Converters::class)
data class Hospital(
    @PrimaryKey
    val id: String,
    val name: String,
    val type: HospitalType,
    val district: String,
    val state: String = "Karnataka",
    val address: String,
    val pincode: String,
    val phone: String,
    val alternatePhone: String? = null,
    val email: String? = null,
    val latitude: Double,
    val longitude: Double,
    val schemesAccepted: List<String>,
    val facilities: List<Facility>,
    val specialties: List<String>,
    val isGovernment: Boolean,
    val bedCapacity: Int? = null,
    val hasEmergency: Boolean = true,
    val operatingHours: String = "24x7",
    val accreditation: String? = null
)

enum class HospitalType {
    GOVERNMENT,
    PRIVATE_EMPANELLED,
    CHARITABLE,
    TEACHING_HOSPITAL
}

data class Facility(
    val name: String,
    val isAvailable: Boolean
)

// Predefined facilities
object HospitalFacilities {
    val ICU = Facility("ICU", true)
    val EMERGENCY = Facility("Emergency Department", true)
    val OPERATION_THEATER = Facility("Operation Theater", true)
    val BLOOD_BANK = Facility("Blood Bank", true)
    val PHARMACY = Facility("Pharmacy", true)
    val DIAGNOSTICS = Facility("Diagnostic Center", true)
    val AMBULANCE = Facility("Ambulance Service", true)
    val MATERNITY = Facility("Maternity Ward", true)
}
```

FILE: Document.kt
```kotlin
package com.arogyaniidhi.data.models

data class Document(
    val id: String,
    val name: String,
    val isMandatory: Boolean,
    val description: String,
    val copies: String,
    val validityRequirement: String?,
    val whyNeeded: String,
    val obtainFrom: String,
    val onlineApplicationUrl: String? = null,
    val category: DocumentCategory,
    var isChecked: Boolean = false
)

enum class DocumentCategory {
    IDENTITY_PROOF,
    ADDRESS_PROOF,
    INCOME_PROOF,
    AGE_PROOF,
    CASTE_CERTIFICATE,
    EMPLOYMENT_PROOF,
    FINANCIAL,
    MEDICAL_RECORDS,
    OTHER
}

object CommonDocuments {
    val AADHAAR = Document(
        id = "aadhaar",
        name = "Aadhaar Card",
        isMandatory = true,
        description = "Unique Identification document",
        copies = "Original + 2 photocopies",
        validityRequirement = "Valid Aadhaar",
        whyNeeded = "Identity and address verification",
        obtainFrom = "UIDAI office or online at uidai.gov.in",
        onlineApplicationUrl = "https://uidai.gov.in",
        category = DocumentCategory.IDENTITY_PROOF
    )
    
    val INCOME_CERTIFICATE = Document(
        id = "income_cert",
        name = "Income Certificate",
        isMandatory = true,
        description = "Annual family income certificate",
        copies = "Original + 1 photocopy",
        validityRequirement = "Issued within last 3 months",
        whyNeeded = "Verify income eligibility for schemes",
        obtainFrom = "Tahsildar office",
        category = DocumentCategory.INCOME_PROOF
    )
    
    val BPL_CARD = Document(
        id = "bpl_card",
        name = "BPL Card",
        isMandatory = false,
        description = "Below Poverty Line Card",
        copies = "Original + 2 photocopies",
        validityRequirement = "Valid BPL card",
        whyNeeded = "Eligibility for BPL-specific schemes",
        obtainFrom = "Local Panchayat or Municipal office",
        category = DocumentCategory.INCOME_PROOF
    )
    
    val RATION_CARD = Document(
        id = "ration_card",
        name = "Ration Card",
        isMandatory = true,
        description = "Family ration card",
        copies = "Original + 1 photocopy",
        validityRequirement = null,
        whyNeeded = "Family composition verification",
        obtainFrom = "Food and Civil Supplies Department",
        category = DocumentCategory.ADDRESS_PROOF
    )
    
    // Add 15+ more common documents...
}
```

### 4.3 ELIGIBILITY ENGINE

**Prompt for AI Code Generator:**

```
Create comprehensive Eligibility Engine for scheme matching:

FILE: EligibilityEngine.kt
```kotlin
package com.arogyaniidhi.domain.eligibility

import com.arogyaniidhi.data.models.*

class EligibilityEngine {
    
    fun calculateEligibleSchemes(
        quizState: QuizState,
        allSchemes: List<HealthScheme>
    ): List<HealthScheme> {
        return allSchemes.filter { scheme ->
            isEligible(quizState, scheme)
        }.sortedByDescending { it.coverageAmount }
    }
    
    private fun isEligible(
        quizState: QuizState,
        scheme: HealthScheme
    ): Boolean {
        val criteria = scheme.eligibilityCriteria
        
        // State check
        if (criteria.applicableStates.isNotEmpty()) {
            if (quizState.selectedState !in criteria.applicableStates) {
                return false
            }
        }
        
        // Income check
        quizState.annualIncome?.let { income ->
            criteria.minIncome?.let { min ->
                if (income < min) return false
            }
            criteria.maxIncome?.let { max ->
                if (income > max) return false
            }
        }
        
        // BPL requirement check
        if (criteria.requiresBPL) {
            if (quizState.hasBPLCard != true) {
                return false
            }
        }
        
        // Occupation check
        if (criteria.applicableOccupations.isNotEmpty()) {
            quizState.occupation?.let { occupation ->
                if (occupation !in criteria.applicableOccupations) {
                    return false
                }
            }
        }
        
        // Family size check
        quizState.familySize?.let { size ->
            criteria.familySizeMin?.let { min ->
                if (size < min) return false
            }
            criteria.familySizeMax?.let { max ->
                if (size > max) return false
            }
        }
        
        return true
    }
    
    fun getEligibilityScore(
        quizState: QuizState,
        scheme: HealthScheme
    ): Int {
        var score = 0
        
        // Higher score for better match
        quizState.annualIncome?.let { income ->
            scheme.eligibilityCriteria.maxIncome?.let { max ->
                if (income <= max * 0.5) score += 30
                else if (income <= max * 0.75) score += 20
                else score += 10
            }
        }
        
        if (quizState.hasBPLCard == true && scheme.eligibilityCriteria.requiresBPL) {
            score += 40
        }
        
        quizState.occupation?.let { occupation ->
            if (occupation in scheme.eligibilityCriteria.applicableOccupations) {
                score += 30
            }
        }
        
        return score
    }
}
```

### 4.4 MOCK DATA - schemes.json

**Prompt for AI Code Generator:**

```
Create complete schemes.json with all major health schemes:

FILE: app/src/main/assets/schemes.json
```json
{
  "schemes": [
    {
      "id": "ayushman_bharat",
      "name": "Ayushman Bharat Pradhan Mantri Jan Arogya Yojana (PM-JAY)",
      "shortName": "Ayushman Bharat",
      "type": "HEALTH_INSURANCE",
      "level": "CENTRAL_GOVERNMENT",
      "coverageAmount": 500000,
      "description": "World's largest health insurance scheme providing free healthcare coverage to economically vulnerable families",
      "benefits": [
        "Coverage of ₹5 lakh per family per year",
        "Cashless treatment at empanelled hospitals",
        "Covers secondary and tertiary hospitalization",
        "Pre and post-hospitalization expenses covered",
        "No restriction on family size, age, or gender",
        "Covers pre-existing diseases from day one",
        "1,393 medical packages covered",
        "Portable across India"
      ],
      "eligibilityCriteria": {
        "maxIncome": 100000,
        "requiresBPL": false,
        "applicableStates": ["Karnataka", "All India"],
        "applicableOccupations": ["FARMER", "AGRICULTURAL_LABOUR", "DAILY_WAGE_WORKER", "UNEMPLOYED"],
        "specialConditions": ["SECC 2011 database", "State-specific criteria"]
      },
      "requiredDocuments": [
        "aadhaar",
        "ration_card",
        "income_cert",
        "residence_proof",
        "family_id_proof"
      ],
      "applicationProcess": "Apply at nearest Common Service Centre (CSC) or Ayushman Mitra desk at empanelled hospitals",
      "contactInfo": {
        "helplineNumber": "14555",
        "email": "pmjay@nha.gov.in",
        "websiteUrl": "https://pmjay.gov.in",
        "officeAddress": "National Health Authority, New Delhi"
      },
      "isActive": true,
      "launchYear": 2018,
      "websiteUrl": "https://pmjay.gov.in"
    },
    {
      "id": "karnataka_arogya_sanjivani",
      "name": "Karnataka Arogya Sanjivani (Vajpayee Arogya Sri)",
      "shortName": "KAPS",
      "type": "HEALTH_INSURANCE",
      "level": "STATE_GOVERNMENT",
      "coverageAmount": 300000,
      "description": "Karnataka state health insurance scheme for BPL and vulnerable families",
      "benefits": [
        "Coverage up to ₹3 lakh per family",
        "Cashless treatment in network hospitals",
        "Covers 1,500+ medical procedures",
        "No age limit",
        "Pre-existing diseases covered",
        "Maternity benefits included"
      ],
      "eligibilityCriteria": {
        "maxIncome": 300000,
        "requiresBPL": false,
        "applicableStates": ["Karnataka"],
        "specialConditions": ["Karnataka resident", "BPL/APL card holder"]
      },
      "requiredDocuments": [
        "aadhaar",
        "bpl_card",
        "ration_card",
        "income_cert",
        "residence_proof_karnataka"
      ],
      "applicationProcess": "Visit Suvarna Arogya Suraksha Trust office or empanelled hospital",
      "contactInfo": {
        "helplineNumber": "080-22065757",
        "email": "info@sast.in",
        "websiteUrl": "https://sast.karnataka.gov.in",
        "officeAddress": "Suvarna Arogya Suraksha Trust, Bengaluru"
      },
      "isActive": true,
      "launchYear": 2010,
      "websiteUrl": "https://sast.karnataka.gov.in"
    },
    {
      "id": "rajiv_aarogyashree",
      "name": "Rajiv Aarogyashree Scheme",
      "shortName": "Rajiv Aarogyashree",
      "type": "HEALTH_INSURANCE",
      "level": "STATE_GOVERNMENT",
      "coverageAmount": 200000,
      "description": "Karnataka's tertiary care health insurance for BPL families",
      "benefits": [
        "Free tertiary care treatment",
        "Coverage for 1,100+ procedures",
        "Includes surgeries, diagnostics, medicines",
        "Transportation allowance provided",
        "Food charges covered during hospitalization"
      ],
      "eligibilityCriteria": {
        "requiresBPL": true,
        "applicableStates": ["Karnataka"],
        "specialConditions": ["Must have BPL card"]
      },
      "requiredDocuments": [
        "aadhaar",
        "bpl_card",
        "ration_card"
      ],
      "applicationProcess": "Register at empanelled hospital with BPL card",
      "contactInfo": {
        "helplineNumber": "1800-425-2505",
        "websiteUrl": "https://aarogyashree.karnataka.gov.in",
        "officeAddress": "Aarogyashree Trust, Karnataka"
      },
      "isActive": true,
      "launchYear": 2012,
      "websiteUrl": "https://aarogyashree.karnataka.gov.in"
    },
    {
      "id": "yashasvini",
      "name": "Yashasvini Cooperative Farmers Health Care Scheme",
      "shortName": "Yashasvini",
      "type": "HEALTH_INSURANCE",
      "level": "COOPERATIVE",
      "coverageAmount": 200000,
      "description": "Health insurance for farmers and rural workers in Karnataka",
      "benefits": [
        "Coverage up to ₹2 lakh",
        "Low annual premium",
        "Covers surgical procedures",
        "For farmers and cooperative society members"
      ],
      "eligibilityCriteria": {
        "applicableStates": ["Karnataka"],
        "applicableOccupations": ["FARMER", "AGRICULTURAL_LABOUR"],
        "specialConditions": ["Cooperative society member"]
      },
      "requiredDocuments": [
        "aadhaar",
        "cooperative_membership",
        "farmer_id"
      ],
      "applicationProcess": "Apply through cooperative society",
      "contactInfo": {
        "helplineNumber": "080-22863245",
        "websiteUrl": "https://www.yashasvini.kar.nic.in"
      },
      "isActive": true,
      "launchYear": 2003,
      "websiteUrl": "https://www.yashasvini.kar.nic.in"
    },
    {
      "id": "janani_suraksha",
      "name": "Janani Suraksha Yojana",
      "shortName": "JSY",
      "type": "MATERNITY_BENEFIT",
      "level": "CENTRAL_GOVERNMENT",
      "coverageAmount": 6000,
      "description": "Safe motherhood intervention scheme providing cash assistance for pregnant women",
      "benefits": [
        "Cash assistance for institutional delivery",
        "₹1,400 for rural areas (general category)",
        "₹1,000 for urban areas",
        "₹600 for home delivery by skilled birth attendant",
        "Free delivery care at government facilities",
        "Three antenatal checkups"
      ],
      "eligibilityCriteria": {
        "gender": "FEMALE",
        "specialConditions": ["Pregnant women", "BPL families prioritized", "Up to 2 live births"]
      },
      "requiredDocuments": [
        "aadhaar",
        "bpl_card",
        "antenatal_card",
        "bank_account"
      ],
      "applicationProcess": "Register at nearest ANM or ASHA worker",
      "contactInfo": {
        "helplineNumber": "1800-180-1104",
        "websiteUrl": "https://nhm.gov.in/jsy"
      },
      "isActive": true,
      "launchYear": 2005,
      "websiteUrl": "https://nhm.gov.in"
    },
    {
      "id": "rbsk",
      "name": "Rashtriya Bal Swasthya Karyakram",
      "shortName": "RBSK",
      "type": "CHILD_HEALTH",
      "level": "CENTRAL_GOVERNMENT",
      "coverageAmount": 0,
      "description": "Child health screening and treatment program for 0-18 years",
      "benefits": [
        "Free health screening for children",
        "Covers 30 health conditions",
        "Free treatment at government facilities",
        "Covers birth defects, developmental delays, disabilities",
        "Screening at schools and anganwadis"
      ],
      "eligibilityCriteria": {
        "minAge": 0,
        "maxAge": 18,
        "applicableStates": ["All India"],
        "specialConditions": ["Universal coverage for all children"]
      },
      "requiredDocuments": [
        "birth_certificate",
        "school_id"
      ],
      "applicationProcess": "Automatic screening through schools and health workers",
      "contactInfo": {
        "helplineNumber": "1800-180-1104",
        "websiteUrl": "https://nhm.gov.in/rbsk"
      },
      "isActive": true,
      "launchYear": 2013,
      "websiteUrl": "https://nhm.gov.in"
    }
  ]
}
```

Continue with hospitals.json, documents.json...

---

## 5. FRONTEND IMPLEMENTATION GUIDE

### 5.1 GRADLE DEPENDENCIES

**Prompt for AI Code Generator:**

```
Create build.gradle.kts with all required dependencies:

FILE: app/build.gradle.kts
```kotlin
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.arogyaniidhi"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.arogyaniidhi"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0.0"
    }

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    // Core Android
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    
    // Lifecycle & ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    
    // Navigation Component
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.6")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.6")
    
    // Room Database
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")
    
    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    
    // Gson for JSON parsing
    implementation("com.google.code.gson:gson:2.10.1")
    
    // RecyclerView
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    
    // CardView
    implementation("androidx.cardview:cardview:1.0.0")
    
    // Splash Screen API
    implementation("androidx.core:core-splashscreen:1.0.1")
}
```

---

### 5.2 NAVIGATION GRAPH

**Prompt for AI Code Generator:**

```
Create Navigation Graph for all screens:

FILE: app/src/main/res/navigation/nav_graph.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<navigation xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/nav_graph"
    app:startDestination="@id/splashFragment">

    <fragment
        android:id="@+id/splashFragment"
        android:name="com.arogyaniidhi.ui.splash.SplashFragment"
        android:label="Arogya-Nidhi">
        <action
            android:id="@+id/action_splash_to_quiz_step1"
            app:destination="@id/step1StateFragment"
            app:enterAnim="@anim/slide_in_right"
            app:exitAnim="@anim/slide_out_left"
            app:popEnterAnim="@anim/slide_in_left"
            app:popExitAnim="@anim/slide_out_right" />
    </fragment>

    <fragment
        android:id="@+id/step1StateFragment"
        android:name="com.arogyaniidhi.ui.quiz.Step1StateFragment"
        android:label="Select State - Step 1/5">
        <action
            android:id="@+id/action_step1_to_step2"
            app:destination="@id/step2IncomeFragment" />
    </fragment>

    <fragment
        android:id="@+id/step2IncomeFragment"
        android:name="com.arogyaniidhi.ui.quiz.Step2IncomeFragment"
        android:label="Annual Income - Step 2/5">
        <action
            android:id="@+id/action_step2_to_step3"
            app:destination="@id/step3OccupationFragment" />
    </fragment>

    <fragment
        android:id="@+id/step3OccupationFragment"
        android:name="com.arogyaniidhi.ui.quiz.Step3OccupationFragment"
        android:label="Occupation - Step 3/5">
        <action
            android:id="@+id/action_step3_to_step4"
            app:destination="@id/step4BPLFragment" />
    </fragment>

    <fragment
        android:id="@+id/step4BPLFragment"
        android:name="com.arogyaniidhi.ui.quiz.Step4BPLFragment"
        android:label="BPL Status - Step 4/5">
        <action
            android:id="@+id/action_step4_to_step5"
            app:destination="@id/step5FamilyFragment" />
    </fragment>

    <fragment
        android:id="@+id/step5FamilyFragment"
        android:name="com.arogyaniidhi.ui.quiz.Step5FamilyFragment"
        android:label="Family Size - Step 5/5">
        <action
            android:id="@+id/action_step5_to_results"
            app:destination="@id/resultsFragment"
            app:popUpTo="@id/splashFragment"
            app:popUpToInclusive="false" />
    </fragment>

    <fragment
        android:id="@+id/resultsFragment"
        android:name="com.arogyaniidhi.ui.results.ResultsFragment"
        android:label="Eligible Schemes">
        <action
            android:id="@+id/action_results_to_documents"
            app:destination="@id/documentChecklistFragment" />
        <action
            android:id="@+id/action_results_to_hospitals"
            app:destination="@id/hospitalFinderFragment" />
        <argument
            android:name="quizState"
            app:argType="com.arogyaniidhi.data.models.QuizState" />
    </fragment>

    <fragment
        android:id="@+id/documentChecklistFragment"
        android:name="com.arogyaniidhi.ui.documents.DocumentChecklistFragment"
        android:label="Document Checklist">
        <argument
            android:name="schemeId"
            app:argType="string" />
    </fragment>

    <fragment
        android:id="@+id/hospitalFinderFragment"
        android:name="com.arogyaniidhi.ui.hospitals.HospitalFinderFragment"
        android:label="Find Hospitals" />

</navigation>
```

---

## 6. ROOM DATABASE IMPLEMENTATION

**Prompt for AI Code Generator:**

```
Create complete Room Database setup:

FILE: AppDatabase.kt
```kotlin
package com.arogyaniidhi.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.arogyaniidhi.data.local.database.dao.*
import com.arogyaniidhi.data.local.database.entities.*

@Database(
    entities = [
        QuizStateEntity::class,
        SchemeEntity::class,
        HospitalEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    
    abstract fun quizStateDao(): QuizStateDao
    abstract fun schemeDao(): SchemeDao
    abstract fun hospitalDao(): HospitalDao
    
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "arogya_nidhi_database"
                )
                .fallbackToDestructiveMigration()
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
```

---

## 7. DEVELOPMENT ROADMAP

### Phase 1: Setup & Core (Week 1)
- [✓] Project initialization
- [✓] Dependencies setup
- [✓] Navigation structure
- [✓] Data models creation
- [✓] Room Database setup

### Phase 2: UI Development (Week 2-3)
- [ ] Splash screen
- [ ] Quiz stepper (all 5 steps)
- [ ] Results screen with scheme cards
- [ ] Document checklist
- [ ] Hospital finder

### Phase 3: Business Logic (Week 4)
- [ ] Eligibility engine
- [ ] Scheme matching algorithm
- [ ] Local data loading (JSON)
- [ ] Repository pattern implementation

### Phase 4: Testing & Polish (Week 5)
- [ ] Unit tests for eligibility logic
- [ ] UI testing
- [ ] Offline functionality verification
- [ ] Performance optimization

---

## 8. TESTING & DEPLOYMENT

### Test Cases:

1. **Eligibility Logic Tests:**
   - Income-based matching
   - BPL card requirement validation
   - State-specific scheme filtering
   - Occupation-based eligibility

2. **Offline Functionality:**
   - Quiz completion without internet
   - Scheme results display offline
   - Hospital search offline

3. **UI/UX Tests:**
   - Stepper navigation
   - Form validation
   - Search functionality
   - Filter operations

---

## 📦 FINAL DELIVERABLE PACKAGE

Upload this complete SOP document to Antigravity or any AI code generator with the prompt:

**MASTER PROMPT:**
```
Generate a complete Android application based on the Arogya-Nidhi SOP document.

Requirements:
1. Create all screens as specified in sections 3.1-3.9
2. Implement data models from section 4
3. Build eligibility engine from section 4.3
4. Include all Karnataka hospitals from section 3.9
5. Use Repository Pattern architecture
6. Implement offline-first with Room Database
7. Follow Material Design 3 guidelines
8. Include all health schemes: Ayushman Bharat, KAPS, Rajiv Aarogyashree, Yashasvini, JSY, RBSK
9. Add stepper UI for quiz flow
10. Implement search and filter for hospitals by district

Generate production-ready code with:
- Complete build.gradle with dependencies
- All Fragment and ViewModel classes
- XML layouts for each screen
- Navigation graph
- Repository classes
- Room Database with DAOs
- JSON data files
- String resources
- Color and theme resources

Target: Fully functional offline health scheme eligibility checker for Karnataka with 30+ empanelled hospitals searchable by district.
```

---

**Document Version:** 2.0  
**Created for:** Arogya-Nidhi Android App  
**Compatible with:** Antigravity, Cursor AI, GitHub Copilot, ChatGPT Code Interpreter  
**Last Updated:** [Current Date]

---

This comprehensive SOP provides everything needed to generate the complete Arogya-Nidhi application by simply uploading to AI code generation tools. Every screen, every data model, every business rule is documented with precise prompts.