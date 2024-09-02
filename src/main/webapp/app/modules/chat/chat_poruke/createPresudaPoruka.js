export const createPresudaPoruka = `
Dobijaš ulogu kreatora teksta presude, na osnovu poslatih primera i podataka nove presude za koju je potrebno napraviti tekst.
Tvoj zadatak je da vratiš tekst presude koji bi što vernije pratio format teksta iz primera, sa informacijama nove presude gde je to potrebno.
U narednim porukama će biti poslati primeri presuda čiji formati bi trebalo da pratiš.
U poslednjoj poruci će biti poslate informacije presude u JSON formatu za koju bi trebalo da napraviš tekst.
Svaka presuda iz primera ima iste podatke kao i presuda za koju praviš tekst, identifikuj te podatke u primerima kako bi mogao da razumeš kako da generišeš tekst nove presude.
Potrudi se da identifikuješ na osnovu naziva informacija gde bi u formatu teksta bilo najbolje da budu smeštene, i smesti informacije iz nove presude tako da tekst presude ima smisla.
Potrudi se da što vernije pratiš format presuda iz primera, redosled podataka iz primera i tvog teksta presude bi trebalo da budu što sličnije.
Obavezno je da podaci budu raspoređeni u paragrafima kao što je to urađeno u primerima.
Obavezno je da ne koristiš "placeholder" vrednosti iz uglastih zagrada u svom konačnom tekstu.
`;

export const createPresudaPrimer1 = `
[kod presude]
U IME CRNE GORE
[naziv suda], prvostepeni krivični sud, u vijeću sastavljanom od sudije [ime sudije predsednika veća] -
predsjednika vijeća, sudija [imena sudija članova veća], članova vijeća, uz učešće samostalnog
referenta - zapisničara [ime zapisničara], odlučujući po optužnici [ustanova koja je podigla optužnicu],
[kod optužnice] od [datum optužnice] godine, protiv okrivljenog [ime optuženog] iz [prebivalište optuženog], zbog krivičnog djela ubistvo [u pokušaju ukoliko je podatak pokušaj true] iz
čl. [broj glavnog člana zakona presude] [naziv zakona glavnog člana presude] („Sl list RCG“, br. 70/03, 13/04, 47/06 i „Sl list CG“ br.
40/08, 25/10, 32/11, 64/11, 40/13, 56/13, 42/15, 58/15, 44/17 i 49/18 – u daljem tekstu KZ CG), u sticaju sa
krivičnim djelom nedozvoljeno držanje oružja i eksplozivnih materija iz čl. 403 st. 1 KZCG, nakon održanog
usmenog, javnog i glavnog pretresa u prisustvu državnog tužioca VDT, [ime tužioca], okrivljenog [ime optuženog], njegovog
branioca advokata [ime branioca], oštećenog maloljetnog [ime oštećenog], njegovog zakonskog zastupnika oca G. A. i
punomoćnika advokata D. R., dana [datum presude] godine, javno je objavio
P R E S U D U
Okrivljeni [ime optuženog], JMBG [jmbg optuženog], od oca [ime oca optuženog] i majke [ime majke optuženog], rođen [datum rođenja optuženog] godine u [mesto rođenja optuženog], sa
prebivalištem u [prebivalište optuženog], državljanin [država za koju ima državljanstvo optuženi], razumije crnogorski jezik, a njegov je albanski,
[zaposlenje optuženog], [bračni status optuženog], otac [broj dece optuženog (tekst)] punoljetne djece, sa završenom [tip obrazovanja optuženog],
[imovinsko stanje optuženog] imovnog stanja, [prethodne presude, ukoliko ih nema, neosuđivan], u pritvoru boravio od [datum početka pritvora] godine do [datum presude] godine.
[KRIV JE ukoliko je krivica true, NEVIN JE ukoliko je krivica false]
Zato što je:
[naziv suda]
[kod presude]
[datum presude]
-Dana [datum radnje] godine, oko 20:45 časova, u [mesto radnje], sposoban da shvati značaj svojih djela
i upravlja svojim postupcima, svjestan zabranjenosti svojih radnji, htio njihovo izvršenje, pokušao da liši
života oštećenog, maloljetnog [ime oštećenog], na način što se svojim putničkim vozilom ''Audi'', reg. br. GS ..., kojim
se kretao seoskim putem, uključio na lokalni put G. – V. i stvorio na tom putu prepreku oštećenom koji se
iz pravca G. kretao putničkim vozilom ''VW Pasat'', reg. br. GS ..., nakon čega je, kada je oštećeni zaustavio
vozilo, iz [oružje korišćeno da nanese povrede], koji je protivno odredbama čl. 5 i 33
Zakona o oružju (Sl. list CG 10/2015) neovlašćeno nosio, ispalio više metaka u njegovom pravcu i sa tri
projektila pogodio vozilo, od kojih je jedan projektil prošao kroz otvoreni prozor na prednjim lijevim
vratima i pored glave oštećenog, a zatim udario u staklo na prozoru prednjih desnih vrata, jedan projektil
u lijeva zadnja vrata i jedan projektil u lijevi zadnji blatobran,
-čime je izvršio krivično djelo ubistvo u pokušaju iz čl. 143 u vezi čl. 20 KZ CG, u sticaju sa krivičnim djelom
nedozvoljeno držanje oružja i eksplozivnih materija iz čl. 403 st. 1 KZ CG.
- za koja mu sud primjenom citiranih zakonskih propisa te čl. 2, 4, 5, 13, 15, 32, 36, 42, 45, 46, 48, 51
Krivičnog zakonika Crne Gore ( KZ CG), te čl. 226, 229 i 374 Zakonika o krivičnom postupku (ZKP-a), u t v r đ
u j e
-za krivično djelo ubistvo u pokušaju iz čl. 143 u vezi čl. 20 KZ CG, kaznu zatvora u trajanju od 3 (tri) godine
i 10 (deset) mjeseci
-za krivično djelo nedozvoljeno držanje oružja i eksplozivnih materija iz čl. 403 st. 1 KZ CG, kaznu zatvora
od 4 (četiri) mjeseca
I primjenom i čl. 48 KZ CG, istog
O S U Đ U J E
Na jedinstvenu kaznu zatvora od [dužina pritvora], u koju kaznu mu se kao izdržano uračunava vrijeme
provedeno u pritvoru od 01.04.2021. godine pa do 25.02.2022. godine.
Okrivljeni se obavezuje da na ime [primalac novca] plati iznos od [količina novca] €, iznos od [količina novca] € na ime ostalih troškova,
kao i [primalac novca] u iznosu od [količina novca] €, te troškovi
punomoćnika oštećenog o kojim troškovima će sud odlučiti posebnim rješenjem a sve prednje u korist
budžeta Crne Gore, na žiro račun br. 832-7114-98 u roku od 15 dana po pravosnažnosti presude pod
prijetnjom prinudnog izvršenja.
Oštećeni [ime oštećenog], se radi ostvarivanja imovinsko pravnog zahtjeva upućuje na parnicu.
`;

export const createPresudaPrimer2 = `
[kod presude]
U IME CRNE GORE
[naziv suda], u vijeću sastavljenom od sudije [ime sudije predsednika veća] predsjednika vijeća, i
članova vijeća sudija [imena sudija članova veća], uz učešće samostalnog referenta zapisničara
[ime zapisničara], u krivičnom predmetu protiv okrivljenog [ime optuženog] iz [prebivalište optuženog], zbog krivičnog djela ubistvo [u pokušaju ukoliko je podatak pokušaj true]
iz [broj glavnog člana zakona presude] [naziv zakona glavnog člana presude], na javnom glavnom pretresu u prisustvu državnog tužioca [ime tužioca], 
okrivljenog [ime optuženog] i njegovog branioca adv. [ime branioca], dana [datum presude] godine, donio je i objavio,
P R E S U D U
Okrivljeni [ime optuženog], JMBG [jmbg optuženog], od oca [ime oca optuženog] i [ime majke optuženog], rođen [datum rođenja optuženog] godine u [mesto rođenja optuženog], sa
prebivalištem u [prebivalište optuženog], državljanin [država za koju ima državljanstvo optuženi], [bračni status optuženog], otac [broj dece optuženog (tekst)] djece od kojih je [broj maloletne dece optuženog (tekst)]
maloljetno, po zanimanju [zaposlenje optuženog], pismen sa završenom [tip obrazovanja optuženog], [imovinsko stanje optuženog] imovnog stanja,
[prethodne presude, ukoliko ih nema, neosuđivan],
[naziv suda]
[kod presude]
[datum presude]
[KRIV JE ukoliko je krivica true, NEVIN JE ukoliko je krivica false]
Zato što je:
Dana, [datum radnje] godine, oko 23,45 h. u [mesto radnje],
sposoban da shvati značaj svog djela i da upravlja svojim postupcima, svjestan zabranjenosti svojih djela i
htio njihovo izvršenje, pokušao da liši života [ime oštećenog] iz P, na način što je u toku svađe i tuče između oštećenih
[ime oštećenog] i R. H, iza pojasa [oružje korišćeno da nanese povrede], koji je
suprotno čl. 33 Zakona o oružju (Sl. List CG, br. 10/2015), neovlašćeno nosio na javnom mjestu, prišao do
oštećenog [ime oštećenog] i više puta ga drškom pištolja udario u [deo tela povrede], a zatim iz istog pištolja ispalio jedan
projektil u oštećenog [ime oštećenog], koji projektil ga je pogodio u predjelu [deo tela povrede], svjestan da ga na taj
način može lišiti života, pa je to i htio, kojom prilikoim mu je nanio tjelesne povrede i to: [spisak nanetih povreda],

Istom radnjom, svjestan da na taj način može izvršiti krivčno djelo, olako držeći da do toga neće doći, iz
nehata lišio života svog unuka oštećenog R. H, na način što je projektil koji je ispalio iz pištolja, po
prolasku kroz meka tkiva oštećenog M. C, pogodio u predjelu grudi oštećenog R. H, koji se nalazio
neposredno iza oštećenog M. C, nanoseći mu tešku tjelesnu povredu, opasnu po život u vidu rane
ustreline, čiji je ulazni otvor u desnom prsnom predjelu, koja se kanalom nastavlja kroz potkožno meko
tkivo, ulazi u grudnu duplju lomeći IV i V desno rebro i nastavlja se kroz srčanu kesu, srce i pluća i završava
u X grudnom pršljenu, pa je usled iskrvarenja iz rascjepa pluća i srca i raskidanih krvnih sudova, duž
kanala strijelne rane i stanjem šoka zbog gubitka krvi, kod oštećenog R.H, nastupila smrt.
- čime je zvršio krivično djelo ubistvo u pokušaju iz čl. 143 u vezi čl. 20 Krivičnog zakonika Crne Gore prema
oštećenom M. C, u sticaju sa krivičnim djelima nedozvoljeno držanje oružja i eksplozivnih materija iz čl.
403 st. 1 Krivičnog zakonika Crne Gore i nehatno lišenje života iz čl. 148 Krivičnog zakonika Crne Gore,
prema oštećenom sada pok. R. H,
-pa mu sud, primjenom citiranih zakonskih propisa, te čl. 2, 4, 5, 13, 15, 32, 41, i 51 Krivičnog zakonika Crne
Gore (Kz-a CG) i čl. 226, 229 i 374 Zakonika o krivičnom postupku Crne Gore (Zkp-a CG), u t v r đ u j e:
-za krivično djelo ubistvo u pokušaju iz čl. 143 u vezi čl. 20 Krivičnog zakonika Crne Gore, i primjenom čl. 45
i 46 KZ-a CG, kaznu od 3 (tri) godine,
-za krivično djelo nedozvoljeno držanje oružja i eksplozivnih materija iz čl. 403 st. 1 Krivičnog zakonika
Crne Gore, kaznu od 6 (šest) mjeseci,
- a za krivično djelo nehatno lišenje života iz čl. 148 Krivičnog zakonika Crne Gore, i primjenom čl. 16
Krivičnog zakonika Crne Gore izvršenog na štetu sada pok. R. H, kaznu zatvora od 8 (osam) mjeseci,
-pa ga sud, na osnovu čl. 48 st. 1 i st. 2 tač. 2 KZ-a CG,
O S U Đ U J E
Na jedinstvenu kaznu zatvora u trajanju od [dužina pritvora], u koju kaznu mu se uračunava vrijeme
provedeno u pritvoru od 13. 02. 2020 godine, pa do 05. 02. 2021 godine.
Okrivljeni se obavezuje da na ime [primalac novca] plati iznos od [količina novca] €, iznos od [količina novca] € na ime ostalih troškova,
kao i [primalac novca] u iznosu od [količina novca] €, te troškovi
punomoćnika oštećenog o kojim troškovima će sud odlučiti posebnim rješenjem a sve prednje u [primalac novca].
Imovinsko-pravnog zahtjeva nije bilo.
`;
