# Arogya-Nidhi Android App

Arogya-Nidhi is an offline-first Android application developed using Kotlin and Jetpack Compose to help citizens identify suitable government healthcare schemes and discover empanelled hospitals in Karnataka.

## Problem Statement

Many people are unaware of government healthcare schemes, eligibility requirements, and required documentation. This application simplifies healthcare scheme discovery through an interactive eligibility quiz and offline hospital search system.

## Features

- Splash/home screen with onboarding instructions
- 5-step eligibility quiz
- Offline eligibility engine
- Government healthcare scheme recommendations
- Interactive document checklist
- Karnataka hospital finder
- District-based filtering and search
- Embedded offline hospital dataset
- Material 3 modern UI

## Supported Healthcare Schemes

- Ayushman Bharat PM-JAY
- Karnataka Arogya Sanjeevani / KAPS
- Rajiv Aarogyashree
- Yashasvini Cooperative Farmers Health Care Scheme
- Janani Suraksha Yojana
- Rashtriya Bal Swasthya Karyakram

## Tech Stack

- Kotlin
- Jetpack Compose
- Material 3
- Android Studio
- Gradle

## Project Structure

```text
app/
gradle/
README.md
build.gradle.kts
settings.gradle.kts

## Application Architecture

The application follows a modular Android architecture using Jetpack Compose for UI development. The app uses offline datasets and eligibility logic to provide healthcare scheme recommendations without requiring internet connectivity.

Modules:
- UI Screens
- Eligibility Engine
- Scheme Data Layer
- Hospital Finder
- Document Checklist
