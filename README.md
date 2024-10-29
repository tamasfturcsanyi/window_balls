# Turcsányi Tamás (CQUG9B) SPECIFIKÁCIÓ

## Rövid ismertetés

A Programozás alapjai 3 tárgyhoz készülő házi feladatom, egy labdákat szimuláló program.
A program 2 dimenzióban szimulál labdákat, ahogy pattognak és összeütköznek.

A program három módban fog működni.

1. Szimuláció

2. Rekord

3. Röplapda

### Szimuláció

Ebben a módban a felhasználó tervezhet és lejátszhat egy szimulációt melyben három szereplő van.
- labda
- fix kör
- téglafal

A szimulációnak meghatározhatja a sebességét, gravitációját és, hogy mennyire vesszen el energia(milyen gyorsan áljanak meg a labdák). A szimulációt megtudja állítani, felgyorsítani vagy újra indítani.

A falaknak és fix köröknek meg lehet adni a méretét, helyzetét és színét. A labdáknak méretét, helyzetét, színét, tömegét, pattogóságát. A fix elemek mint a körök és falakról csak lepattannak a labdák, ők maguk nem mozdulnak.

A szimulációra az is hatással van hogyan mozog a program ablaka az asztalon. Ha megrázzuk akkor a falakról lepatannak a labdák.
A szimulációkat el lehet menteni fáljba és késöbb megnyitni. Minden elmentődik a szimulációról, még az egyes labdák sebessége is.

### Rekord

Ez egy árkád stílusu mini játék melyben az ablak mozgatásával kell a labdát a mindig új helyen feltűnő célba juttatni. Egy időzítő leszámol és ha nullához ér vesztettél. Minden sikeresen elért célpont egy kis időt ad a vissza számlálóhoz és egy pontot ad a pontszámhoz. A játék megjegyzi mi volt az eddig elért legnagyobb pontszám.

### Röplapda

A Röplapda egy kétszemélyes játék amit két emberi játékos játszhat. Két térfélen van két eleven labda amit a két játékos irányít. Egy téglafal választja el őket. Egy labda feldobásával kezdetét veszi a játék. Ha egy játékos térfelén földet ér a labda veszít. Ezt megakadályozhatja úgy, hogy alámegy és átfejeli a túloldalra.

Az elérendő győzelmek száma, két játékos milyen színű eleven labdát irányít mind testreszabhatóak.

## Use-Case-ek

### FőMenü

A programban egy menün keresztül érhetjük el a különböző módokat. A menükben az egérrel lehet rányomni a különböző opciókra.

### Szimuláció

#### Menü
A szimuláció menüjében egyik gomb egy üres szimulációt nyit meg. A másik pedig a program mappájában lévő szimulációk listáját nyitja meg, melyek közül választhat. Ha rányom egy szimuláció gombjára kijelöli azt. A kijelölt szimulációval két dolgot tehet.
- megnyitás
- törlés

#### Futó
A futó szimulációt egy az ablak tetején látható kazetta lejátszóra emlékeztető gombok vezérelnek.
- Lelassítja
- Elindítják/megállítják
- Felgyorsítja
- Kilép

#### Álló
Az álló szimulációban van a mentés gomb, ami pedig egy új ablakot nyit meg.
Itt megadhatjuk dialog box-okkal, hogy új fáljba, vagy a régibe mentse illetve a nevét a szimulációnak.
Egy fogaskerék gomb megnyomásával állíthatjuk be a szimuláció paramétereit egy új ablakban.
Egy háromszög alakú gombbal elinditjuk a szimulációt.
Egy plusz szimbólumu gomb megnyomásával az új szereplő ablak nyílik meg. Itt adjuk meg a szereplő paramétereit.

Ha rányomunk egy szereplőre akkor kijelöljük azt. A kijelölt szereplőt:
- mozgathatjuk az egérrel
- törölhetjük egy gombbal
- megduplázhatjuk egy gombbal

### Rekord

A rekord gomb megnyomásával elindul a játék. Nincsen semmi beállítandó. Irányítani trükkösen, az ablak mozgatásával lehet. A hátérben látszik majd a pontunk, rekordunk és hátra lévő időnk.

Ha vesztünk kiírja pontszámunk és két gomb tűnik fel.
- Újrakezdés
- Kilépés

### Röplapda

#### Menü
Itt beállítjuk, hány pontig menjen a mérkőzés, milyen színű eleven labdával játszanak a játékosok. Egy gombal pedig elindul a mérkőzés.

#### Játékban
Az első játékos wasd gombokkal mozog, ugrik. A második a nyilakat használja.A sarokban lévő pause gombbal megállíthatjuk. A pause menü két új gombot tüntet fel.
- Kilépés 
- Folytatás

Ha az egyik játékos nyer, kiírja pontszámunk és két gomb tűnik fel.
- Újrakezdés
- Kilépés

## Megoldási ötletek
### Ablak és Menük 
Java Swing által nyújtott osztályok segítségével.

### Formák színek.
A Java AWT geom által adott osztályok segítségével.

### Szerializálás
A szimuláció mentését és visszatöltését a JSON fáljformátummal fogom megoldani.
A GSON library-t fogom használni a JSON-ba átírás, olvasáshoz.