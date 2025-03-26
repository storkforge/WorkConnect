## Future plans:

<img width="1440" alt="Screenshot 2025-03-26 at 09 34 43" src="https://github.com/user-attachments/assets/55b2b4b3-ac94-47d6-8022-0cf4b43200c2" />

Vi kommer att bygga ett spring boot project med 6 personer. men först och främst jag vill att du ska skapa ui för oss för ett hemsida. Här ser du 2 bilder om ungefär hur våran app kommer att see ut. 

här har du våran app idee om hur den kommer fungera.

1. Övergripande idé
WorkConnect ska vara en plattform där unga kan:
✅ Skapa professionella profiler
✅ Hitta och ansöka om praktikplatser och jobb
✅ Få AI-baserade jobbrekommendationer
✅ Ansluta sig till nätverk och mentorprogram
✅ Lära sig om karriärmöjligheter via artiklar och guider
✅ Få notiser om relevanta event och utbildningar

2. Teknisk Arkitektur och Planering
Här anpassar vi labbens tekniska krav till WorkConnect:

🛠️ Backend - Spring Boot
Spring MVC & Thymeleaf – För att bygga en webbapp där unga kan söka jobb och nätverka
Spring Security – Social login med Google, Facebook och LinkedIn
Rollbaserad behörighet – Användare (Jobbsökare) & Premium (Rekryterare/HR)
REST API & GraphQL – Jobbannonser kan nås via både API och frontend
🖥️ Frontend & UI
HTML + Thymeleaf – För att presentera jobblistor, nätverksmöjligheter
REST API & GraphQL – För att hämta dynamiska data
Internationellt språkstöd – Engelska, Svenska och fler språk
📡 Integrationer & AI
Externa API:er – Koppling till jobbportaler och LinkedIn
Spring AI – Personliga rekommendationer baserade på användarprofil
Redis Cache – För att snabba upp jobbannonser och sökresultat
⚡ CI/CD & DevOps
GitHub Actions – Automatiserad testning och deployment
Pull Requests – Samarbete i teamet innan kod merger
3. Funktioner och MVP
📌 MVP (Minimum Viable Product) – Vad ni ska bygga först

Registrering & Social Login (Google/Facebook/LinkedIn)
Profil-skapande (CV, erfarenhet, intressen)
Jobbannonser (Arbetsgivare kan posta, användare kan ansöka)
AI-baserade jobbrekommendationer
Evenemang & Karriärtips
4. Projektplan och GitHub Issues
💡 Hur ni organiserar arbetet i GitHub Project:
✅ Sprint 1 – Setup & Grundfunktioner

Skapa GitHub Repo & Projektplan
Sätta upp Spring Boot Backend
Implementera Användarregistrering & Login
✅ Sprint 2 – Grundläggande UI & Jobbannonser

Bygga Thymeleaf-sidor för jobbannonser
Implementera REST API för jobbdata
Koppla på Redis-caching
✅ Sprint 3 – AI & Rekommendationer

Implementera Spring AI för jobbrekommendationer
Lägga till GraphQL API
✅ Sprint 4 – Slutjustering & Deployment

Testning
CI/CD med GitHub Actions


