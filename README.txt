Niestety serwer bazy danych kt�r� pierwotnie u�ywa�em zosta� usuni�ty, przez co program b�dzie zawsze si� �le uruchamia�. 
Na dysk doda�em plik konfiguracyjny "hibernate.cfg.xml" kt�ry nale�y skopiowa� do folderu "Project\src\main\resources".
Zawiera on konfiguracj� nowego serwera kt�ry ju� dzia�a prawid�owo.

Przez wzgl�d na fakt �e wykorzystywany jest darmowy serwer bazy danych dodawania do niej nowych element�w jest czasoch�onne
(ok. 20-30 min) dlatego nie zalecam u�ywa� opcji "Aktualizuj baz� danych SQL". Wczytywanie danych z bazy danych odbywa si�
na pocz�tku progamu i nie jest procesm d�ugim.