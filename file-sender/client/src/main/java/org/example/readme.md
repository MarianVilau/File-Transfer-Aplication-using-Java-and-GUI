## Design Patterns și Principii de Proiectare în codul pentru client

### Design Patterns (Modele de Proiectare):
1. **Factory Method Pattern**: Clasa `FileSelectionPanelFactory` acționează ca o fabrică pentru crearea instanțelor clasei `FileSelectionPanel`. Aceasta încapsulează logica de creare a obiectelor, oferind o modalitate de a crea obiecte `FileSelectionPanel` fără a expune logica de instanțiere în codul client.

2. **Observer Pattern**: Clasa `FileSelectionPanel` implementează șablonul observer prin permiterea mai multor observatori (`FileSelectionObserver`) de a se abona și de a primi notificări atunci când un fișier este selectat. Clasa `SendFileObserver` acționează ca un observator și efectuează acțiuni atunci când un fișier este selectat, cum ar fi actualizarea interfeței de utilizator sau inițierea trimiterii fișierului într-un fir de execuție separat.

### Principii de Proiectare:
1. **Încapsulare**: Codul demonstrează încapsularea prin încapsularea funcționalităților asociate în clase, cum ar fi clasa `FileSelectionPanel` și `SendFileHandler`. Fiecare clasă încapsulează propriul comportament și date, furnizând interfețe bine definite și ascunzând detaliile de implementare.

2. **Modularitate**: Codul este modular, cu diferite componente (clase) responsabile de sarcini specifice. De exemplu, clasa `Client` gestionează logica principală a aplicației, clasa `FileSelectionPanel` se ocupă de interfața utilizator pentru selectarea unui fișier, iar clasa `SendFileHandler` se ocupă de funcționalitatea de trimitere a fișierului. Această modularitate îmbunătățește întreținerea și reutilizarea codului.

3. **Separarea Responsabilităților**: Codul separă diferitele responsabilități în clase distincte. Clasa `FileSelectionPanel` se concentrează pe interfața utilizator pentru selectarea fișierelor, clasa `SendFileHandler` gestionează funcționalitatea de trimitere a fișierelor, iar clasa `Client` administrează fluxul aplicației. Această separare permite o organizare mai bună și înțelegerea mai ușoară a responsabilităților fiecărei clase.

În ansamblu, codul evidențiază o combinație de modele de proiectare (Factory Method și Observer) și principii de proiectare (încapsulare, modularitate și separarea responsabilităților) pentru a realiza un design bine structurat și extensibil.
