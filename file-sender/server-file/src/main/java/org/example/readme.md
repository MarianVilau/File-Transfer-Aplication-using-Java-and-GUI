# Descriere

Acest proiect implementează un sistem simplu de selecție și afișare a fișierelor într-o aplicație desktop. Utilizează anumite design patterns și principii de design pentru a obține o arhitectură modulară și extensibilă.

## Design Patterns

1. **Observer Pattern (Patternul Observatorului)**: Implementat în relația dintre clasa `FileSelectionPanel` (Observable) și clasa `FileServer` (Observer). Clasa `FileSelectionPanel` menține o listă de obiecte `FileSelectionListener` și le notifică atunci când un fișier este selectat.

## Principii de Design

1. **Single Responsibility Principle (Principiul Responsabilității Unice)**: Codul respectă acest principiu prin separarea responsabilităților în clase distincte. Fiecare clasă are o singură responsabilitate, de exemplu, `FileSelectionPanel` gestionează selecția fișierelor, `FileServer` se ocupă de operațiile cu fișiere, `PreviewFrame` afișează previzualizarea fișierelor, iar clasa `FileRow` reprezintă componenta UI pentru fiecare fișier.

2. **Open/Closed Principle (Principiul Deschis/Închis)**: Codul respectă principiul OCP prin posibilitatea de extindere fără modificare. Clasa `FileSelectionPanel` poate primi noi ascultători (listeners) fără a schimba implementarea sa, prin utilizarea patternului Observer.

3. **Dependency Inversion Principle (Principiul Inversiunii Dependințelor)**: Codul respectă principiul DIP prin dependența de abstractizări în locul implementărilor concrete. Clasa `FileSelectionPanel` depinde de interfața `FileSelectionListener`, ceea ce permite flexibilitate și posibilitatea de înlocuire ușoară a diferiților ascultători.

4. **Interface Segregation Principle (Principiul Segregării Interfețelor)**: Codul demonstrează ISP prin furnizarea de interfețe specifice (`FileSelectionListener`) pentru diferite responsabilități, permițând clienților să implementeze doar metodele de care au nevoie.

5. **Liskov Substitution Principle (Principiul Substituției Liskov)**: Codul respectă principiul LSP prin asigurarea faptului că obiectele de tipul interfeței `FileSelectionListener` (de exemplu, `FileServer`) pot fi înlocuite cu instanțe ale subtipurilor lor (de exemplu, `PreviewFrame`) fără a afecta corectitudinea programului.

6. **Separation of Concerns (Separarea Responsabilităților)**: Codul separă diferitele responsabilități în clase distincte (`FileSelectionPanel`, `FileServer`, `PreviewFrame`, `FileRow`), obținând astfel o modularizare și ușurință în întreținere.

În ansamblu, codul demonstrează o combinație de design patterns (Observer pattern) și principii de design (SRP, OCP, DIP, ISP, LSP, Separation of Concerns) pentru a promova modularitatea, extensibilitatea și ușurința în întreținere.
