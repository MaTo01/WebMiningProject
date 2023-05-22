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
<li>Estrarre i 50 termini con maggior peso e memorizzarli in un file di testo (txt), dove ciascuna riga deve essere nel formato: “<termine> <peso>”.</li>
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
