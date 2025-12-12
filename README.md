# DemoVetShop

This project is a compact Java demo application that models day-to-day workflows of a small veterinary clinic / private vet shop.
It was actually commissioned to me by a veterinarian living close to me who was looking for a quick prototype for a new site,
this way he could then hire contractors knowing what to ask for.
I embarked on the project to put to test my skills as a software designer for low abstraction modules, I practiced applying rigorous design patterns
to achieve clear and reliable information management in multi-language systems.
This README presents a balanced view for both non-technical hypotethical owners/staff and developers: 
how to try the prototype, what features are already implemented, and how the code is organized so future extensions are straightforward.


Quick summary (user-oriented)
- Purpose: rapid prototype to validate clinic workflows: user registration, pet management, appointment requests, slot blocking by admin, visit registration and attaching medicines from a small catalog.
- What you can do right away:
  - Register a user (Cliente, Veterinario or Amministratore) and create pet records.
  - Use the pet chip code to request/book an appointment.
  - From the daily list, select a booking and register a visit (type, description, cost) and add prescribed medicines.
  - As admin, block whole days or specific hourly slots so clients cannot book those times.
- UI: built with Swing; intuitive, guided dialogs allow selecting animals, dates/times, appointments and drugs. Visual screens include:
  - FormRegistrazioneUtente (registration entry point)
  - FormRichiediPrenotazione / RichiediPrenotazione (booking flow)
  - FormSelezionaSlot / SelezionaSlot (admin slot blocking)
  - FormRegistraVisita / RegistraVisita (visit registration and drug selection)
  - Supporting dialogs: SelezionaAnimale, SelezionaData, SelezionaPrenotazione, SelezionaFarmaco

 
Positive highlights and strengths (developers)
- Complete end-to-end flows: registration → pet management → booking → visit registration → attaching medicines — everything needed to validate the core business process is implemented and interactive.
- User-friendly Swing UI with focused dialogs and visual backgrounds, making the prototype presentable to a shop owner and easy to demo in person.
- Thoughtful domain modeling: separation between DAOs, Entities, DTOs and Controllers keeps the codebase modular and easy to extend.
- Date/time UX: integration with LGoodDatePicker delivers a robust date/time selection experience (veto policies, hourly slot menus, Sunday disabled for clinic closure).
- Reusable singletons: Agenda and CatalogoFarmaci provide intuitive caching semantics for bookings and medicine catalog, improving demo responsiveness.
- Clear, compact DAO patterns: DAO classes encapsulate SQL queries by entity and map results into DTOs that the UI consumes.
- Dialog-driven selection flows: selection dialogs (animals, dates, bookings, medicines) are implemented so a demo attendee can immediately explore and exercise workflows.
- Easy to demo: multiple entry-point main methods and minimal external dependencies mean you can start and demo the app quickly from an IDE.

Technical overview 
- Languages & tools: Java (100% of the repo). Swing for UI, JDBC for DB access, and LGoodDatePicker library for date/time pickers used in the booking and slot dialogs.
- High-level architecture:
  - boundary (UI): Swing windows and dialogs implementing user workflows and validation; main entry forms for registration, booking, slot selection, and visit registration.
  - control: static controllers (ControllerPrenotazione, ControllerRegistraUtente, ControllerRegistraVisita, ControllerSlotBloccati) that orchestrate UI → entity/DAO interactions and return user-friendly messages.
  - entity: core domain objects and singletons encapsulating in-memory logic and composition (Agenda, CatalogoFarmaci, Cliente, Prenotazione, SlotBloccato, Visita, Farmaco, Utente).
    - Agenda: singleton that composes bookings and blocked slots and produces an overall list of unavailable times for the UI.
    - CatalogoFarmaci: singleton that loads drug catalog from DB and exposes DTOs for UI lists.
  - DTOs: lightweight transfer objects (AnimaleDomesticoDTO, FarmacoDTO, PrenotazioneDTO, SlotBloccatoDTO) used to pass minimal domain data to the UI.
  - database (DAO layer): DAO_* classes for CRUD access (DAO_Cliente, DAO_Amministratore, DAO_Veterinario, DAO_AnimaleDomestico, DAO_Prenotazione, DAO_SlotBloccato, DAO_Farmaco, DAO_Visita). DBConnectionManager centralizes JDBC connection creation.
- Design notes:
  - Clear separation of concerns (UI → Controllers → Entity/DAO → Database).
  - Use of DTOs keeps GUI code decoupled from database representations.
  - Singletons for agenda and catalog simplify the demo lifecycle and make it easy to load cached lists to show in the UI quickly.
  - GUI input validation is integrated (e.g., date/time selection, name/email/password formats, visit fields).

How to run the demo (practical steps)
Prerequisites
- JDK 11+ installed.
- MySQL accessible locally or remotely.
- The Date/Time picker library: LGoodDatePicker (used by the date/time components).
- Images used by the UI are expected in resources under /images (used by background panels).

Basic setup and run
1. Configure the DB connection in src/database/DBConnectionManager.java:
   - url, dbName, userName, password (current defaults point to a local MySQL instance).
2. Create the database and tables (example schema below). You can put these statements into schema.sql and run them on your MySQL instance.
3. Seed a few demo records (clients, animals, farmaci) so the UI shows data on first run.
4. In your IDE run one of the main UI frames (each form contains a main method for quick startup):
   - boundary.FormRegistrazioneUtente (starter for registration flows)
   - boundary.FormRichiediPrenotazione (starter for booking flows)
   - boundary.FormSelezionaSlot (starter for admin slot management)
   - boundary.FormRegistraVisita (starter for visit registration)
5. Interact with the GUI: register users, add/select animals, request bookings, block slots, and register visits with drug selection.

Extension ideas (positive, optional enhancements you can add)
- Package the demo with a small Maven/Gradle build and add a docker-compose that includes MySQL for a reproducible developer/demo environment.
- Add a small seed.sql to populate the DB with sample clients, animals and a drug catalog so the owner can try the flows immediately.
- Create a short screencast or a few screenshots to show the owner the main workflows (registration, booking, visit registration).
- Add a lightweight reporting screen (bookings per day) and an export (CSV) button for demo reporting.

Developer notes & where to look in the code
- DB connection: src/database/DBConnectionManager.java — central place to configure the DB.
- DAOs: src/database/* — per-entity persistence operations and ResultSet → DAO mapping.
- Entities & singletons: src/entity/* — business logic entrypoints (Agenda, CatalogoFarmaci) and domain objects.
- DTOs: src/DTOs/* — lightweight objects passed to Swing dialogs for display.
- Controllers: src/control/* — use these to understand how UI calls translate to domain operations.
- UI / boundary: src/boundary and src/boundary/secondari — main forms and selection dialogs; look at main methods in Form* classes for quick startup.



Enjoy demoing the prototype! — it’s a compact, demonstrable implementation of the essential workflows of basically
any phisical business dealing with large volumes of customers. 


TEST NOTE:
Recommended minimal DB schema (copy into schema.sql)
```sql
CREATE DATABASE IF NOT EXISTS ProgettoAmbulatorio;
USE ProgettoAmbulatorio;

CREATE TABLE CLIENTI (
  idCliente INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(100),
  cognome VARCHAR(100),
  email VARCHAR(150) UNIQUE,
  username VARCHAR(100),
  password VARCHAR(255)
);

CREATE TABLE AMMINISTRATORI (
  idAmministratore INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(100),
  cognome VARCHAR(100),
  email VARCHAR(150) UNIQUE,
  username VARCHAR(100),
  password VARCHAR(255)
);

CREATE TABLE VETERINARI (
  idVeterinario INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(100),
  cognome VARCHAR(100),
  email VARCHAR(150) UNIQUE,
  username VARCHAR(100),
  password VARCHAR(255)
);

CREATE TABLE ANIMALI (
  chipCode INT PRIMARY KEY,
  nome VARCHAR(100),
  tipo VARCHAR(100),
  razza VARCHAR(100),
  colore VARCHAR(50),
  datadinascita DATE,
  Cliente_idCliente INT,
  FOREIGN KEY (Cliente_idCliente) REFERENCES CLIENTI(idCliente)
);

CREATE TABLE PRENOTAZIONI (
  idPrenotazione INT AUTO_INCREMENT PRIMARY KEY,
  `data` DATETIME,
  Animale_chipCode INT,
  FOREIGN KEY (Animale_chipCode) REFERENCES ANIMALI(chipCode)
);

CREATE TABLE VISITE (
  idVisita INT AUTO_INCREMENT PRIMARY KEY,
  tipo VARCHAR(100),
  costo DOUBLE,
  Prenotazione_idPrenotazione INT,
  descrizione TEXT,
  FOREIGN KEY (Prenotazione_idPrenotazione) REFERENCES PRENOTAZIONI(idPrenotazione)
);

CREATE TABLE FARMACI (
  idFarmaco INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(200),
  produttore VARCHAR(200)
);

CREATE TABLE VISITA_HAS_FARMACO (
  Visita INT,
  Farmaco INT,
  PRIMARY KEY (Visita, Farmaco),
  FOREIGN KEY (Visita) REFERENCES VISITE(Prenotazione_idPrenotazione),
  FOREIGN KEY (Farmaco) REFERENCES FARMACI(idFarmaco)
);

CREATE TABLE blocchi (
  id INT AUTO_INCREMENT PRIMARY KEY,
  slot DATETIME UNIQUE
);
```
