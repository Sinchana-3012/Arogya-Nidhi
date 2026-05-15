# Arogya-Nidhi Android App

Native Android implementation of the Arogya-Nidhi SOP using Kotlin, Jetpack Compose, and Material 3.

## What is included

- Splash/home screen with quote and onboarding instructions
- 5-step eligibility quiz for state, annual income, occupation, BPL status, and family size
- Offline eligibility engine for:
  - Ayushman Bharat PM-JAY
  - Karnataka Arogya Sanjeevani / KAPS
  - Rajiv Aarogyashree
  - Yashasvini Cooperative Farmers Health Care Scheme
  - Janani Suraksha Yojana
  - Rashtriya Bal Swasthya Karyakram
- Per-scheme document checklist with interactive checkboxes
- Karnataka hospital finder with district filters and text search
- Embedded offline data for 30 empanelled hospitals

## Open in Android Studio

1. Open this folder as a Gradle project.
2. Let Android Studio sync dependencies.
3. Run the `app` configuration on an emulator or Android device.

The app is intentionally offline-first: scheme, checklist, and hospital data are compiled into the app, so no backend is required for the prototype.
