Niestety serwer bazy danych któr¹ pierwotnie u¿ywa³em zosta³ usuniêty, przez co program bêdzie zawsze siê Ÿle uruchamia³. 
Na dysk doda³em plik konfiguracyjny "hibernate.cfg.xml" który nale¿y skopiowaæ do folderu "Project\src\main\resources".
Zawiera on konfiguracjê nowego serwera który ju¿ dzia³a prawid³owo.

Przez wzgl¹d na fakt ¿e wykorzystywany jest darmowy serwer bazy danych dodawania do niej nowych elementów jest czasoch³onne
(ok. 20-30 min) dlatego nie zalecam u¿ywaæ opcji "Aktualizuj bazê danych SQL". Wczytywanie danych z bazy danych odbywa siê
na pocz¹tku progamu i nie jest procesm d³ugim.