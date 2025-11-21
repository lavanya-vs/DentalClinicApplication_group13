
# 🦷 DentIQ – Smart Dental Patient Registration System

**DentIQ** is a native Android application designed to streamline **appointment handling, patient–doctor communication, and clinic management** in a modern dental environment.  
The app provides **separate role-based dashboards** for Patients and Doctors with features like appointment booking, billing, medical history, and payment tracking with a clean professional UI.

---

## 🧩 Key Features

### 🔐 Role-Based Access
- Separate login flows for **Patients** and **Doctors**
- Dedicated dashboards for both users

### 📅 Patient Appointment Booking
- Browse available doctors & view specialization
- Check timings & availability
- Book appointment & track status (**Accepted / Pending / Rescheduled**)

### 👨‍⚕️ Doctor Management Dashboard
- View booked appointments
- Accept or reschedule requests
- Access patient details & medical history

### 💳 Billing & Payment Tracking
- Doctors generate bills & update payment status
- Patients view bills and pay via dummy **QR-based module**
- Real-time update to doctor after successful payment

### 📜 Medical History
- View past visits, diagnosis, and notes stored locally

### 🦷 Dental Care Guide
- Oral hygiene tips and awareness section for patients

### 🎨 Modern & Professional UI
- Clean gradient UI with Material 3 elements
- Pinterest-style card layouts and smooth navigation

---

## 🛠 Technology Stack

| Category | Tools |
|---------|-------|
| **Language** | Kotlin |
| **Platform** | Native Android |
| **IDE** | Android Studio |
| **Architecture** | MVVM |
| **Local Database** | Room |
| **Serialization** | Gson |
| **UI** | XML + Material 3 |
| **Payment Simulation** | Dummy QR-based UI |

---

## 📁 Modules Overview

| Module | Description |
|--------|-------------|
| `PatientAppointmentActivity` | Create, view & manage patient appointments |
| `DoctorDashboardActivity` | Doctor dashboard for appointments, billing, patient details |
| `AppointmentDetailActivity` | Approve / Reschedule appointment requests |
| `PatientHistoryActivity` | View history, previous visits & medical notes |
| `BillingActivity` | Add/update billing, track payment status |
| `PaymentActivity` | Patient QR payment simulation |
| `CareGuideActivity` | Dental care tips & awareness guide |

---

## 🤝 Contribution Guidelines

Contributions are always welcome!  
If you have suggestions or feature improvements, feel free to open a Pull Request.

### 🚀 Steps to Contribute

```bash
# 1️⃣ Fork this Repository

# 2️⃣ Create a new Feature Branch
git checkout -b feature/newFeature

# 3️⃣ Commit your Changes
git commit -m "Added new feature"

# 4️⃣ Push to your Branch
git push origin feature/newFeature

# 5️⃣ Open a Pull Request
