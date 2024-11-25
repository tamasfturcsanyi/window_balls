# Window Balls - User Manual
## Rövid ismertetés

Ez egy labdákat szimuláló program.
A program 2 dimenzióban szimulál labdákat, ahogy összeütköznek egymással, és lepattannak az ablak faláról.

A program 3 módban működik:

1. Simulation - Szimuláció

2. Basket - Kosár

3. Volley - Röpi

## Részletes Működés

### Főmenü

Már itt is ki lehet próbálni a szimulációt. Ha mozgatjuk az ablakot a képernyőnkön, látjuk, hogy a feliratok és a gombok egy helyben maradnak, és a labdák meglökődnek az ablak mozgatásától. A gombok is részt vesznek a szimulációban, és lepattannak róluk a labdák. A három módot a három gombbal érhetjük el.
Kilépéshez csak ki kell ikszelni az ablakot. Ez a program többi részén is igaz.

---

### Simulation

#### Simulation Picker

A Simulation Picker-en keresztül nyithatunk meg szimulációkat.
A Simulation Picker két részből áll: felsősorban gombok, alatta szimulációk.

A szimulációk egy görgethető panelen vannak. Ha rákattintunk az egyikre, kijelöljük. Ha megint rákattintunk vagy másik szimulációt jelölünk ki, akkor visszavonjuk a kijelölést az eredetileg kijelölt szimulációról.

A gombok részen négy gomb van. Funkciójuk balról jobbra:

1. Add:

Létrehoz és megnyit egy új, üres szimulációt az alapértelmezett beállításokkal.
2. Open:

Megnyit egy már létező szimulációt. Csak akkor nyomható meg, ha előtte kijelöltünk egy szimulációt.

3. Delete:

Törli a kijelölt szimulációt. Csak akkor nyomható meg, ha előtte kijelöltünk egy szimulációt.

4. Back:

Bezárja a Simulation Picker-t és megnyitja a Főmenüt.

---

#### Simulation Player

A Simulation Playerben lehet különböző szimulációkat létrehozni, változtatni és lejátszani.
A szimuláció az alsó panelen látszik. A felső panellel irányíthatjuk azt.
Két állapota van a Simulation Playernek: Futó és Álló.

Álló módban, ha mozgatjuk az ablakot, a labdák nem mozdulnak a képernyőn.
Álló módban szabadon átméretezhetjük az ablakot.
Ha rányomunk a szimuláció egy szereplőjére, kijelöljük azt. Egyszerre csak egy szereplőt jelölhetünk ki.
Amíg egy szereplő ki van jelölve, addig követi az egér mozgását. Így mozgathatunk szereplőket a szimulációban.
A kijelölt szereplőt törölhetjük a `Delete` billentyű megnyomásával.
A kijelölt szereplőt lemásolhatjuk a `Ctrl` + `C` kombinációval.

Futó módban az ablakmozgatásra reagálnak a labdák.
Futó módban nem méretezhetjük át az ablakot.

A felső sorban található gombok mind használhatóak álló módban. Itt van a funkcionalitásuk balról jobbra haladva.

1. Save:

Csak Álló módban használható.
Megnyitja a Save Dialog-ot. Itt választhatunk, hogy a megnyitott fájlba mentsük a szimulációt, vagy egy újba.
Ha az újat választjuk, akkor meg kell adnunk a szimuláció nevét.
Ha olyan nevet adunk meg, ami már létezik, akkor felülírjuk azt a fájlt.
A `Cancel` gombbal elvetjük a mentést és visszatérünk a Simulation Player-be.
Az `OK` gomb csak akkor megnyomható, ha van megadva fájl, ahova menteni fogunk.
Megnyomásával történik meg a tényleges mentés.

2. Add:

Csak Álló módban használható.
Megnyitja a Create Body Dialog-ot. Itt text field-ekkel és comboboxokkal megadhatunk egy szereplőt.
A `Create` gombot csak helyesen megadott értékek mellett nyomhatjuk meg.
Megnyomásával bezárul a Dialog és hozzáadódik az új szereplő a szimulációhoz.
Ha kiikszeljük a Dialog-ot, elvetjük a hozzáadást.

3. Slow Down:

Csökkenti a szimuláció sebességét 0.25-tel. Nem enged 0 alá.
A szimuláció sebességét ugyanebben a sorban egy Label-ön látható.
Használható Futó állapotban is.

4. Start/Stop

Vált Álló és Futó mód között. Elindítja, illetve megállítja a szimulációt.

5. Speed Up

Ugyanaz, mint a Slow Down, csak gyorsít 0.25-tel.

6. Settings

Csak Álló módban használható.
Itt egy Dialog-on keresztül átírhatjuk a szimuláció paramétereit.
A `Cancel` gombbal elvetjük a változtatást és visszatérünk a Simulation Player-be.
Az `OK` gomb csak akkor nyomható meg, ha minden paraméter helyesen meg van adva.
Megnyomásával történik meg a tényleges mentés.

7. Back

Bezárja a Simulation Player-t és visszatér a Simulation Picker-be.
Álló és Futó módban is használható.

---

### Basket

Egy árkád stílusú játék, ahol meg kell dönteni a rekordot.

Idő alatt a legtöbb zsákolást kell elvégeznünk a narancssárga labdával.
A labdát az ablak képernyőn való mozgatásával lehet manipulálni.
A képernyőn valahol van egy "Palánk", ami két fix labdából áll.
Ha átvisszük lefele a kosárlabdát a palánkon keresztül, zsákolunk.
Minden zsákolásnál kapunk valamennyi időt a hátralévő időnkhöz és egy pontot.
Ha lejár a hátralévő időnk, véget ér a játék.

A képernyőn nagyban látszódik a hátralévő időnk.
Alatta az eddig elért pontunk.
A háttér színe a hátralévő idő szerint változik.

Ha véget ér a játék, megnézhetjük a pontunkat és az eddigi rekordot.
Választhatunk, hogy újrakezdjük a játékot, vagy visszatérünk a főmenübe.

---

### Volley

**Cél**

Ez egy két személyes röplabdára emlékeztető játék.
A két játékos egymás ellen játszik.
Egy fal két részre osztja a játékteret.
A célunk először elérni a kitűzött pontszámot.
Pontot akkor kapunk, ha a labda az ellenfelünk térfelén ér földet.
A saját térfelünkön egy eleven labdát irányítunk.
Azzal tudjuk megakadályozni azt, hogy a labda nálunk érjen földet.

**Irányítás**

Az egyik játékos WASD-vel, a másik a nyilakkal mozog.
A felfelével lehet ugrani, de nem olyan nagyot.
Az igazi magasság eléréséhez ki kell használnunk, hogy labdák vagyunk magunk is.
A lefelével le tudjuk pattintani magunkat.
A játékot megállíthatjuk az `Escape` billentyű megnyomásával.
Ha megállítjuk a játékot, két gomb jelenik meg.
A `Restart` újraindítja a mérkőzést, ugyanazokkal a beállításokkal.
Az `Exit` visszavisz minket a Főmenübe.
Az `Escape` ismételt megnyomásával folytatódhat a játék.
Ha az egyik játékos nyer, ugyanezekkel a gombokkal találkozunk.

**Felállítás**

A játék megkezdése előtt a Volley Starter-be kerülünk.
Itt beállíthatjuk labdánk színét és arcát.
Ezen kívül azt is, hány pontig menjen a mérkőzés.
A Start gomb megnyomásával kezdetét veszi a meccs.
