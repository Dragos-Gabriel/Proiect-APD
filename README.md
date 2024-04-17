Proiect APD

1.	Algoritm și Limbaj de Programare: 
Am implementat algoritmul de căutare a cuvintelor într-un text folosind limbajul Java si scrierea in fisierul CSV. Pentru căutare, am utilizat o metodă de căutare secvențială în text.

2. Informații despre Mașina Utilizată: Codul a fost rulat pe o mașină cu următoarele specificații:
•	Procesor: Intel Core i3 
•	Memorie RAM: 6 GB
•	Sistem de Operare: Windows 10 Pro

3. Rezultate Experimentale Secvential:
Număr de Cuvinte	Timp de Execuție
2000	            186 ms
5000	            400 ms
10000	            900 ms
12000	            2 minute(120000 ms)

4. Rezultate Experimentale Paralel:
Număr de Cuvinte	Timp de Execuție
2000	            66 ms
5000	            200 ms
10000	            700 ms
12000	            900 ms

An folosi thread-uri pentru a procesa diferite fragmente ale textului simultan. Am împărți textul în bucăți egale și am creat un fir de execuție pentru fiecare bucățică, fiecare fir de execuție va număra aparițiile cuvintelor în partea sa de text. La final, am adunat rezultatele parțiale pentru a obține rezultatul final. Mai exact, în clasa TextProcessor, în metoda countWordsSequential, am înlocuit bucla secvențială care numără cuvintele cu o abordare paralelă, astfel încât fiecare fir de execuție să numere cuvintele într-o porțiune separată a textului. Astfel, fiecare fragment de text este procesat de către un fir de execuție separat, ceea ce duce la o execuție mai rapidă a operației de numărare a cuvintelor.

Observații cod secvential:
•	Timpul de execuție variază în funcție de dimensiunea textului. Cu cât textul era mai mare, cu atât timpul de execuție creștea.
•	Performanța poate fi îmbunătățită prin utilizarea altor algoritmi de căutare sau optimizări specifice limbajului de programare.
•	Menționez faptul că aceste rezultate pot varia în funcție de resursele hardware și de sarcinile în curs de desfășurare pe mașină în momentul rulării.

Observații pentru versiunea paralelă:

• Timpul de execuție variază în funcție de dimensiunea textului și de numărul de fire de execuție utilizate. Cu cât textul este mai mare și cu cât sunt mai multe fire de execuție, cu atât timpul de execuție poate fi redus.

• Performanța poate fi îmbunătățită prin utilizarea altor strategii de paralelizare sau optimizări specifice limbajului de programare, cum ar fi folosirea unor biblioteci de paralelism mai eficiente sau utilizarea algoritmilor mai optimi pentru sarcina specifică.

• Este important de remarcat că rezultatele obținute pot varia în funcție de resursele hardware disponibile și de sarcinile concurente care rulează pe mașină în momentul rulării. Este recomandat să se evalueze performanța pe mai multe configurații hardware și condiții de sarcină pentru a obține o înțelegere mai cuprinzătoare a performanței sistemului.
