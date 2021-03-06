# Hvordan lage flappy(crappy)bird!

## Steg for steg  

<br>
<br>
1. Før vi begynner må vi laste inn noen bilder, og sette størrelsen på skjermen vår. Bilder kan lagres i variabeltypen `PImage`. Du kan copypaste koden nedenfor. Kopier linken for å se bildene.

#### Nyttige funksjoner og tips
* Copypaste denne koden til å starte med. Vi skal gå gjennom hva alt brukes til.

``` processing
PImage backImg;
PImage birdImg;
PImage wallImg;
PImage startImg;

boolean gameover = true
int score = 0
int highScore = 0
int birdY = height/2, 
int vbirdY = 0;
int imageX = 0, 
int wx[] = {600, 900};
int wy[] = {300, 600};

void setup() {
  size(600,800);
  fill(0);
  textSize(40);
  backImg =loadImage("http://i.imgur.com/cXaR0vS.png");
  birdImg =loadImage("http://i.imgur.com/mw0ai3K.png");
  wallImg =loadImage("http://i.imgur.com/4SUsUuc.png");
  startImg=loadImage("http://i.imgur.com/U6KEwxe.png");
}
```  
<br><br>

2. Foreløpig skal vi skrive i `draw()` metoden. Første steg er å lage en if-setning som sjekker om det er gameover. Er det gameover så vil vi tegne startImg på skjermen.  

#### Nyttige funksjoner og tips
* `imageMode(CORNER)`, bildene vi tegner vil ta utgangspunkt i hjørnet av bildet når vi bruker x og y.
* `image(startImg, x-koordinat, y-koordinat)`  
<br><br>

3. Start med koden hvor man spiller. Først skal bakgrunnen og fuglen tegnes. Fuglen skal alltid være i midten av skjermen, og den skal kun bevege seg vertikalt (loddrett). Fuglen sin x-koordinat vil dermed alltid være `width/2`. Å tegne bakgrunnsbildet er litt vanskeligere da vi skal få bildet til å bevege seg bortover. Begynn med et stillestående bilde. Sett `imageMode(CORNER)` og deretter tegn bildet med `image(backImg, imageX, 0)`. Du har nå et bilde som dekker hele skjermen.  

#### Nyttige funksjoner og tips
* `imageMode(CORNER)`
* `image(backImg, x-koordinat, y-koordinat)`
* `image(birdImg, x-koordinat, y-koordinat)`  
<br><br>

4. Bevegelse kan skapes ved å legge til linjen `imageX = imageX-6` etter linjen som tegner bildet. Neste gang vi kjører gjennom `draw()` og koden `image(backImg, imageX, 0)` kjøres vil bildet bli tegnet 6 pixler lenger til venstre. Neste gang 12 pixler til venstre, osv. Lar du programmet kjøre lenge nok vil du se at bildet før eller siden slutter, så det må fikses. Bildet er tegnet slik at start og slutt passer sammen, og man kan derfor tegne et nytt bilde på enden av det første med `image(backImg, imageX + backImg.width, 0)`. For å spare tid kan du kopiere koden nedenfor. Se om du skjønner hvorfor vi trenger if-setningen!  

#### Nyttige funksjoner og tips
* Legg til etter første linjen som tegner første bildet

``` processing
image(backImg, imageX+backImg.width, 0);
imageX = imageX - 6;
if(imageX == -1800){
  imageX=0;
}
```  
<br><br>

5. Det må skrives kode for at fuglen skal falle. Her brukes metode som i punkt 3.  
<br><br>

6. Nå faller fuglen, men den gjør det sakte og stødig. Gravitasjon er en flott ting som også trengs i spill. Hvis vi skal oversette gravitasjon til "spillspråk" kan du tenke på en kraft som øker hastigheten til et objekt for hver gang `void draw()` blir kjørt. Fuglen må med andre ord flyttes lenger ned enn forrige gang `void draw()` ble kjørt. Se om du klarer å løse problemet, hvis ikke kan du spørre en av lærerne om hint!  
<br><br>

7. Det vi nå mangler er hindre, få fuglen til å fly, og en gameover skjerm. Vi begynner med hindrene. For å spare tid vil jeg her gi deg koden, men er det noe du ikke skjønner så rop på en av lærerne!  

#### Nyttige funksjoner og tips
* Denne koden kan limes inn rett under koden for fuglen. Linjer som starter med `//` vil bli ignorert av processing.

``` processing
//This draws the two walls. Notice that there's always two walls visible
//in our game.
for(int i = 0 ; i < 2; i++) {
  imageMode(CENTER);
  //Places two walls with a space of 200 pixels between them.
  image(wallImg, wx[i], wy[i] - (wallImg.height/2+100));
  image(wallImg, wx[i], wy[i] + (wallImg.height/2+100));

  //When one pair of walls goes out of the picture on the left,
  //we create a new pair of walls that start at the right side
  //(wx[i] = width), and with a randomly placed hole
  //(wy[i] = (int)random(200,height-200)).
  if(wx[i] < 0) {
    wy[i] = (int)random(200,height-200);
    wx[i] = width;
  }

  //If a wall is at the middle point, it means the bird is about to
  //"pass" the wall.
  if(wx[i] == width/2){
    //We add to our score
    score++;
    //and we check if current score is higher than the highScore and set
    //the highScore variable equal to the highest of the variables score
    //and highScore
    highScore = max(score, highScore);
  }

  //If we go off the screen, or we hit one of the walls, it's gameOver,
  //and we set gamestate to 1
  if(y>height || y<0 || (abs(width/2-wx[i])<25 && abs(y-wy[i])>100)){
    gamestate=1;
  }

  //This moves the pair of walls to the left (just like bckimage)
  wx[i] -= 6;
}
```  
<br><br>

8. Start spillet! Bruk `void mousePressed(){}` for å kjøre kode hvis venstre musetast blir trykket. Dette skal utenfor både `void setup()` og `void draw()`. Inni trenger vi en if-setning som sjekker om vi spiller eller er i "GAMEOVER" modus. Du kan lime inn koden nedenfor, som vil resette alle variabler og starte spillet for oss, men kun hvis vi ikke spiller idet tasten blir trykket.

#### Nyttige funksjoner og tips
* Copypaste kode:

``` processing
if(!gameover) {
  wx[0] = 600;
  wy[0] = y = height/2;
  wx[1] = 900;
  wy[1] = 600;
  x = gamestate = score = 0;
}
```  
<br><br>

9. Få fuglen til å fly! Før if-setningen vår i `mousePressed` trengs EN linje kode. Fuglen trenger et "oppdrift", noe som motvirker tyngdekraften. Lettere sagt så skal "tyngdekraft-variabelen" bli "reversert". Denne er vanskelig å finne ut av, men prøv en stund før du spør læreren!

