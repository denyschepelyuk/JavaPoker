# Projektový plán pro Poker Hru

Poker hra bude digitální implementací klasické poker hry, která umožní hráčům zapojit se do karetní hry vyžadující strategii, pravděpodobnost a psychologii. Hráč se může účastnit různých pokerových her (jako Texas Hold'em nebo Omaha) proti AI protivníkům. Hra bude obsahovat grafické uživatelské rozhraní pro vylepšený uživatelský zážitek spolu s audio efekty.

---

## Přehled architektury projektu

Architektura poker hry bude dodržovat principy objektově orientovaného programování, aby zvýšila modularitu, čitelnost a údržbovatelnost. Projekt bude zahrnovat následující třídy a funkce:

---

## Struktura tříd

### Game Class

**Odpovědnost:**  
Řídí celkový průběh hry, včetně inicializace hry, hlavní smyčky a správy stavu hry.

**Funkce:**
- void run()
- void update()
- void render()
- void handleInput()

---

### Player Class

**Odpovědnost:**  
Představuje hráče (buď vy nebo AI) ve hře, drží jeho ruku karet a spravuje akce.

**Funkce:**
- void makeBet(int amount)
- void fold()
- void check()
- void call()
- int bet()
- ArrayList<Card> getHand()

---

### Card Class

**Odpovědnost:**  
Představuje hrací kartu s jejím vzorem a hodnotou.

**Funkce:**
- std::string getDescription()
- bool isGreaterThan(const Card& other)

---

### Deck Class

**Odpovědnost:**  
Spravuje kolekci karet, včetně promíchání a rozdávání.

**Funkce:**
- void shuffle()
- Card dealCard()
- ArrayList<Card> dealHand(int numCards, Player& player)

---

### Table Class

**Odpovědnost:**  
Představuje konfiguraci pokerového stolu, včetně komunitních karet a banku.

**Funkce:**
- void addCommunityCard(Card card)
- void resetTable()
- int getPotAmount()

---

### AIPlayer Class (dědí od Player)

**Odpovědnost:**  
Implementuje AI logiku pro rozhodování o sázkách na základě stavu hry.

**Funkce:**
- void makeDecision()

---

### UIManager Class

**Odpovědnost:**  
Řídí vykreslování grafického uživatelského rozhraní, včetně tlačítek, informací o hráčích a karet na stole.

**Funkce:**
- void renderGame(const Game& game)
- void displayWinner(const Player& player)

---

### InputHandler Class

**Odpovědnost:**  
Spravuje uživatelský vstup, převádí surový vstup na herní příkazy.

**Funkce:**
- void processInput()
- Command getCommand()
