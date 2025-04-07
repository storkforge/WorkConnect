## Future plans:

<img width="1440" alt="Screenshot 2025-03-26 at 09 34 43" src="https://github.com/user-attachments/assets/55b2b4b3-ac94-47d6-8022-0cf4b43200c2" />

# WorkConnect

WorkConnect is a Spring Boot platform being developed by a team of 6. It aims to connect young professionals with job opportunities, internships, networking possibilities, and career development resources.

## Core Idea

WorkConnect will serve as a comprehensive platform where young individuals can:

-   ✅ Create professional profiles (digital CVs).
-   ✅ Find and apply for internships and jobs.
-   ✅ Receive AI-driven job recommendations tailored to their profile.
-   ✅ Join networking groups and mentorship programs.
-   ✅ Learn about career paths through articles and guides.
-   ✅ Get notified about relevant events and training opportunities.

## UI Design Reference

The user interface (UI) and user experience (UX) will be developed based on the provided design mockups, aiming for an intuitive and professional look and feel suitable for the target audience.

## Technical Architecture

The project leverages the Spring Boot ecosystem and related technologies:

### Backend - Spring Boot
-   **Spring MVC & Thymeleaf:** To build the core web application for job searching and networking.
-   **Spring Security:** Implementing social login options (Google, Facebook, LinkedIn) and securing endpoints.
-   **Role-Based Access Control:** Defining distinct roles like `USER` (Job Seeker) and `PREMIUM` (Recruiter/HR).
-   **REST API & GraphQL:** Providing flexible APIs for accessing job listings and other data, consumable by both the frontend and potentially external clients.

### Frontend & UI
-   **HTML + Thymeleaf:** Rendering dynamic web pages for job listings, user profiles, and networking features.
-   **API Consumption:** Fetching dynamic data via the REST and/or GraphQL APIs.
-   **Internationalization (i18n):** Support for multiple languages, starting with English and Swedish.

### Integrations & AI
-   **External APIs:** Connecting to external job portals and potentially LinkedIn for richer data.
-   **Spring AI:** Implementing personalized job and content recommendations based on user profiles and activity.
-   **Redis Cache:** Caching frequently accessed data like job listings and search results to improve performance.

### CI/CD & DevOps
-   **GitHub Actions:** Setting up automated pipelines for testing and deployment.
-   **Pull Requests:** Utilizing PRs for code review and collaborative development before merging into the main branch.

## Minimum Viable Product (MVP)

The initial focus will be on delivering the following core features:

-   User Registration & Social Login (Google/Facebook/LinkedIn).
-   Profile Creation (CV details, experience, skills, interests).
-   Job Listings (Allowing employers/recruiters to post, and users to search/apply).
-   Basic AI-powered Job Recommendations.
-   Section for Events & Career Tips.

## Project Plan (Sprints)

The development work will be organized using GitHub Projects and Sprints:

### Sprint 1 – Setup & Core Features
-   [ ] Create GitHub Repository & Project Board.
-   [ ] Set up


