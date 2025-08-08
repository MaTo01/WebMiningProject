# WebMiningProject

Progettare ed implementare un sistema software in grado di scaricare (download) articoli da testate giornalistiche online resi disponibili da diverse sorgenti e di estrarre e visualizzare i termini più “importanti” nell’insieme degli articoli scaricati.

<h2><strong>Regole generali</strong></h2>
<ul>
<li>Utilizzare il linguaggio <strong>Java</strong> (unit testing utilizzando JUnit).</li>
<li>Il codice dovrà essere opportunamente commentato e indentato. Commentare il codice in modo da poter utilizzare <strong>Javadoc</strong>.</li>
<li>La documentazione dovrà essere prodotta come <strong>gdoc</strong> o, in alternativa, come pagina Web usando il Maven site plugin.</li>
<li>Per i diagrammi UML si consiglia di utilizzare un tool; si consiglia <strong>draw.io</strong>, ma si possono utilizzare anche altri strumenti come PlantUML, Lucidchart, miro etc.</li>
</ul>

<h2><strong>Richieste particolari</strong></h2>
<ul>
<li>Il sistema deve poter supportare <strong>nuove sorgenti</strong>.</li>
<li>Dopo la fase di download, deve essere effettuata la persistenza su file degli articoli usando lo stesso formato per tutti gli articoli di tutte le sorgenti. Potete utilizzare una <strong>libreria per serializzare e deserializzare</strong> gli articoli. L’implementazione senza libreria è opzionale.</li>
<li>Il sistema deve poter supportare <strong>nuove modalità di memorizzazione ed accesso</strong> agli articoli.</li>
<li>Per estrarre i termini ed il loro peso (numero di documenti in cui appare), è necessario partire dai file in cui gli articoli sono memorizzati (punto 2).</li>
<li>Il sistema deve poter supportare <strong>nuove strutture</strong> per memorizzare ed avere accesso ai termini più importanti.</li>
<li>L’utente deve poter specificare se eseguire solo il download, solo l’estrazione dei termini a partire dai file in cui sono stati memorizzati gli articoli, o entrambe le azioni in sequenza.</li>
</ul>

<h2><strong>Sorgenti</strong></h2>
<ul>
<li>Diversi file <strong>CSV</strong> disponibili su moodle (la prima riga sarà l’header, con la lista di attributi/campi).</li>
<li><strong>The Guardian API</strong> (https://open-platform.theguardian.com/).</li>
<li>È necessaria una <strong>API Key</strong>.</li>
<li>Servirà lo <strong>API Endpoint</strong> per accedere al contenuto (search).</li>
<li>È possibile usare il <strong>client Java</strong> disponibile su github: https://github.com/matarrese/content-api-the-guardian.</li>
</ul>

<h2><strong>Estrazione termini</strong></h2>
<ul>
<li>Con <strong>termine</strong> si intende una parola che compare nel testo dell’articolo (titolo + corpo).</li>
<li>Estrarre i 50 termini con maggior peso e memorizzarli in un file di testo (txt), dove ciascuna riga deve essere nel formato: “termine
  peso”.</li>
<li>Se ci sono pareggi in termini di peso, utilizzare l’<strong>ordine alfabetico</strong> come criterio di ordinamento addizionale.</li>
<li>Per estrarre i termini si può <strong>StringTokenizer</strong> oppure le funzionalità messe a disposizione da una libreria come <strong>CoreNLP</strong>(vedere le Pipeline ed in particolare il “passo” Tokenization).</li>
</ul>

<h2><strong>Consegna</strong></h2>
<ul>
<li><strong>Documento requisiti</strong>. Gli use case rappresentati con diagrammi UML e con descrizione in linguaggio naturale strutturato.</li>
<li><strong>Domain model</strong>. In UML, se necessario con descrizione testuale.</li>
<li><strong>Codice</strong> (java + eventuali file di compilazione/jar).</li>
<li><strong>Test</strong> (documento + report generato dai test)</li>
<li><strong>Manuale</strong>. Deve contenere:
  <ul>
  <li>una panoramica concisa e ad alto livello del progetto (come sono stati implementate le funzionalità richieste)</li>
  <li>istruzioni su come installare ed eseguire il software</li>
  <li>indicazione di quali funzioni sono state riutilizzate da librerie esistenti, specificando la versione delle librerie</li>
  </ul>
</li>
</ul>
  
<h2><strong>Q&A</strong></h2>
<strong>Sorgenti</strong>

<p>
<ul>
<li><strong>Cosa si intende per sorgente?</strong></li>

Un file o un insieme di file (ad esempio in formato CSV o JSON) contenente articoli o un servizio da cui scaricarli (ad esempio le API del The Guardian). Nel progetto è necessario progettare ed implementare le funzionalità per estrarre gli articoli dal CSV e per scaricarli dalle API del The Guardian. In altri termini, le due sorgenti da considerare sono: file CSV e The Guardian API; altre sorgenti non sono necessarie.
  
<li><strong>Cosa intende con "Il sistema deve poter supportare nuove sorgenti"?</strong></li>

Sebbene nel progetto verranno considerate solo due sorgenti, dovete progettare il software in modo che sia possibile aggiungere una nuova sorgente senza la necessità di apportare un numero elevato di modifiche. Ad esempio, se avete modellato una specifica sorgente con una classe e un articolo ottenuto da tale sorgente con un'altra classe, aggiungerete (almeno) due nuove classi ma la parte del software che si occupa di invocare la fase di estrazione/raccolta degli articoli rimarrà pressoché invariata.
  
<li><strong>Si può assumere che gli articoli che otteniamo dalla stessa sorgente abbiano gli stessi attributi?</strong></li>

Si. Potete assumere che la struttura della risposta delle API del The Guardian non cambi, e che gli attributi di un generico articolo memorizzato nel CSV siano sempre gli stessi.
  
<li><strong>Si può assumere che gli articoli che otteniamo dalle diverse sorgenti abbiano gli stessi attributi con gli stessi nomi?</strong></li>

No. Vedere la struttura della risposta delle API del The Guardian e la prima riga del file CSV. 
 
<li><strong>Si può usare un libreria per leggere gli articoli dal file CSV?</strong></li>

Si potete usare un libreria per leggere/estrarre gli articoli dal file CSV. Ne esistono diverse. Una delle possibilità è quella di usare Apache Commons CSV.
  
<li><strong>Quale file CSV dobbiamo utilizzare tra quelli caricati nel sito? v1 o v2?</strong></li>

Potete utilizzare il CSV che desiderate. La seconda versione (nytimes_articles_v2.csv) dovrebbe essere più facile da gestire dal momento che il testo è suddiviso in title (titolo) e body (corpo).
    
<li><strong>Quanti articoli dobbiamo utilizzare?</strong></li>

1000 per da scaricare mediante le API del The Guardian, 1000 dal CSV (sono già 1000 nel file). La query utilizzata per scaricare gli articoli è "nuclear power".
  
<li><strong>Per interagire con le API del The Guardian, dobbiamo necessariamente utilizzare il client Java da lei segnalato?</strong></li> 
  
No. Potete utilizzare direttamente le funzionalità di java.net oppure librerie come Apache HTTP Client.
    
<li><strong>Il client Java per interagire con le API del The Guardian non funziona. Come devo fare?</strong></li>

Utilizzate la versione modificata messa a disposizione nella sezione "Risorse Progetto" della pagina del corso.

<li><strong>Nell'utilizzare le API del The Guardian, dobbiamo specificare un'interrogazione (query) particolare?</strong></li>

La query da utilizzare per replicare i file messi a disposizione è "nuclear power". Dovete specificare la stringa "nuclear power" per il query term q, mantenendo le virgolette. Tale sintassi è utilizzata per specificare che gli articoli da reperire sono quelli in cui compaiono le parole "nuclear" e "power" ed in cui tali parole appaiono una dopo l'altra. Se desiderate approfondire, potete fare riferimento alla documentazione delle API, in particolare alla sezione relativa all'endpoint https://content.guardianapis.com/search.
  
<li><strong>I file JSON contenuti nell'archivio theguardian_articles_v1.zip sono un'altra sorgente da cui estrarre gli articoli? Possiamo estrarre gli articoli dai file JSON invece che dalle API del The Guardian?</strong></li>

Dovete usare le API. Ho inserito il JSON in modo che possiate accedere alla struttura del JSON, creare i test (se pensate possa essere utile). Se desiderate considerare un’altra sorgente che importi da JSON, fate pure (ma solo dopo aver fatto tutto il resto).
</ul>
</p>

<strong>Esecuzione del programma, interazione con utente, presentazione risultati</strong>
<p>
<ul>

<li><strong>Dobbiamo creare una shell per interagire con il software o possiamo eseguirlo da linea di comando?</strong></li>

Potete eseguirlo da linea di comando. Potete gestire direttamente voi gli "argomenti" nel main, usare una libreria come Apache Commons CLI, o strumenti come Spring Shell.
    
<li><strong>Possiamo prevedere una modalità (più) interattiva nella fase di esecuzione del programma, cosi da coinvolgere maggiormente l'utente?</strong></li>

Si, è possibile. Dovete documentarla.
  
<li><strong>Possiamo creare un'interfaccia grafica per intergire con il sistema e visualizzare i risultati?</strong></li>

Si potete crearla. Dovete documentarla adeguatamente. Tutti i membri del gruppo devono conoscere la tecnologia utilizzata (concetti principali), la struttura della UI, ed il suo funzionamento.
  
<li><strong>Dobbiamo prevedere una procedura per "filtrare" i termini, e.g. rimuovendo numeri o termini troppo generici?</strong></li> 

Non è necessario. Se desiderate farlo, potete fare riferimento al codice del progetto di demo messo a disposizione in cui si utilizza CoreNLP. Se desiderate rimuovere alcuni termini come "and", "the", ... potete usare una stoplist, e.g., quella contenuta nel file english_stoplist_v1.txt

</p>
</ul>

<strong>Documentazione</strong>

<p>
<ul>
  
<li><strong>Nel punto 3 della slide 8 (Deliverable), v0.3, è riportato quanto segue: "Design Model. In UML, con descrizione testuale dove necessario. Deve includere modelli strutturali e dinamici." Quali digrammi dobbiamo riportare?</strong></li>

Sono utili i diagrammi di classe ed i diagrammi di sequenza. Potrebbero essere utili altri diagrammi, ad esempio i diagrammi di attività.

</p>
</ul>
  
