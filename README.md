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

În codurile paralele, am folosit thread-uri pentru a realiza paralelismul. Mai exact, în clasa TextProcessor, în metoda countWordsSequential, am înlocuit bucla secvențială care numără cuvintele cu o abordare paralelă, astfel încât fiecare fir de execuție să numere cuvintele într-o porțiune separată a textului. Astfel, fiecare fragment de text este procesat de către un fir de execuție separat, ceea ce duce la o execuție mai rapidă a operației de numărare a cuvintelor.

Observații:
•	Timpul de execuție variază în funcție de dimensiunea textului. Cu cât textul era mai mare, cu atât timpul de execuție creștea.
•	Performanța poate fi îmbunătățită prin utilizarea altor algoritmi de căutare sau optimizări specifice limbajului de programare.
•	Menționez faptul că aceste rezultate pot varia în funcție de resursele hardware și de sarcinile în curs de desfășurare pe mașină în momentul rulării.
