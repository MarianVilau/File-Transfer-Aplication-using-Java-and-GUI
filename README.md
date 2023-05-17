## Descriere

Această aplicație constă într-un sistem de transfer de fișiere între un client și un server. Clientul este responsabil pentru selectarea și trimiterea fișierelor, în timp ce serverul primește fișierele și le afișează într-o interfață grafică.

### Client

Interfața clientului este reprezentată de clasa `Client`. Aici este creată fereastra principală a clientului și se utilizează `FileSelectionPanelFactory` pentru a crea un panou de selecție a fișierelor. De asemenea, se utilizează pattern-ul Observer prin adăugarea unui observator (`SendFileObserver`) la panoul de selecție a fișierelor. Astfel, atunci când un fișier este selectat, se inițiază trimiterea acestuia într-un fir de execuție separat.

### Server

Interfața serverului este reprezentată de clasa `Application`. În metoda `main`, se creează și se inițializează serverul. Serverul utilizează pattern-ul Observer prin implementarea interfeței `FileSelectionListener` și adăugarea sa ca ascultător la panoul de selecție a fișierelor (`FileSelectionPanel`). Astfel, când un fișier este selectat în panoul de selecție a fișierelor de către client, serverul primește notificarea și deschide un nou socket pentru a accepta fișierul trimis de client.

Clasa `FileServer` este responsabilă pentru gestionarea serverului. Aici se inițializează fereastra principală a serverului și panoul de selecție a fișierelor (`FileSelectionPanel`). Serverul utilizează un `ServerSocket` pentru a asculta conexiuni de la clienți. Când un client se conectează și trimite un fișier, serverul primește fișierul și îl adaugă în lista sa internă de fișiere (`myFiles`). Apoi, serverul actualizează panoul de selecție a fișierelor pentru a afișa noul fișier primit.

Clasa `PreviewFrame` reprezintă fereastra de previzualizare a fișierului. Aceasta este deschisă atunci când un utilizator selectează un fișier în panoul de selecție a fișierelor. Fereastra afișează numele fișierului și oferă utilizatorului opțiunea de a descărca fișierul sau de a renunța la aceasta.

Clasa `FileRow` reprezintă componenta UI pentru afișarea unui fișier în panoul de selecție a fișierelor. Fiecare fișier este afișat ca un rând cu numele fișierului.

Clasa `MyFile` reprezintă obiectul de date pentru un fișier. Conține informații despre numele fișierului și conținutul său sub formă de array de bytes. De asemenea, oferă metode pentru a obține extensia fișierului.

## Pattern-uri de design și principii de design

### Pattern-ul Observer

Pattern-ul Observer este utilizat în această aplicație pentru a notifica serverul atunci când un fișier este selectat de către client. Interfața `FileSelectionListener` este definită pentru a fi implementată de către orice obiect care dorește să primească notificări despre selecția de fișiere. Atunci când un fișier este selectat în panoul de selecție a fișierelor, acesta notifică toți ascultătorii înregistrați, inclusiv serverul.

### Pattern-ul Factory Method

Clasa `FileSelectionPanelFactory` acționează ca o fabrică pentru crearea instanțelor clasei `FileSelectionPanel`. Aceasta încapsulează logica de creare a obiectelor, oferind o modalitate de a crea obiecte `FileSelectionPanel` fără a expune logica de instanțiere în codul client.

### Principiul Single Responsibility

Fiecare clasă din aplicație are o singură responsabilitate și se concentrează pe un aspect specific al sistemului. De exemplu, clasa `Client` se ocupă de interfața clientului și de inițierea trimiterii fișierelor, în timp ce clasa `FileServer` se ocupă de gestionarea serverului și de primirea fișierelor.

### Principiul Open/Closed

Principiul Open/Closed este respectat în aplicație prin utilizarea interfețelor și a extinderii claselor în locul modificării acestora. De exemplu, interfața `FileSelectionListener` permite adăugarea de noi ascultători pentru evenimentele de selecție a fișierelor, fără a modifica codul existent. De asemenea, clasa `FileSelectionPanelFactory` poate fi extinsă pentru a crea alte tipuri de panouri de selecție a fișierelor, fără a modifica clasa `Client`.

### Principiul Dependency Inversion

Principiul Dependency Inversion este respectat prin utilizarea dependințelor prin intermediul interfețelor. De exemplu, clasa `Client` depinde de interfața `FileSelectionListener` și nu de implementarea specifică a acesteia. Acest lucru permite înlocuirea implementărilor la nevoie și ușurează testarea și refolosirea codului.

### Principiul Separation of Concerns

Principiul Separation of Concerns este aplicat în aplicație prin împărțirea funcționalităților în clase separate. De exemplu, clasa `Client` se ocupă doar de interacțiunea cu utilizatorul și de inițierea trimiterii fișierelor, în timp ce clasa `FileServer` se ocupă de gestionarea serverului și de primirea fișierelor. Această separare facilitează dezvoltarea, testarea și menținerea aplicației.

## Concluzie

Aplicația de transfer de fișiere folosește pattern-uri de design și principii de design pentru a crea o arhitectură modulară, extensibilă și ușor de întreținut. Pattern-ul Observer este utilizat pentru a permite comunicarea între client și server într-un mod decuplat, iar principiile SOLID asigură o structură clară și flexibilă a codului. Prin respectarea acestor pattern-uri și principii, aplicația este scalabilă și poate fi ușor extinsă cu noi funcționalități sau componente în viitor.
