# Projeto Dungeon-and-Wumpus

Join us on an exciting adventure with our Java game, created as the final project for our Object-Oriented Programming university class (due to this the rest of the description was wrote in portuguese). This collaborative effort between my friend and me showcases our passion for programming and game development. What makes this game truly special is the inclusion of a custom CPU rendering engine that we built from scratch and its isometric view. Enjoy seamless graphics and engaging gameplay as we push the boundaries of creativity and technical skills. Get ready for an enjoyable gaming experience filled with fun and excitement!

# Estrutura de Arquivos e Pastas

 A estrutura geral das pastas em detalhes é a seguinte:

```
├── README.md                               <- apresentação do projeto
│
├── _data                                   <- dados usados pelo jogo
│
├── _assets                                 <- desenhos e imagens usados pelo jogo
│
├── _sounds                                 <- Sons e musicas usadas pelo jogo
│
├── src                                     <- arquivos fonte do jogo
│   │
│   └──mc322
│        ├── engine                         <- arquivos relacionados ao funcionamento bruto, com relação a plataforma. arquivos gerais que independem do jogo
│        │     ├── gfx                      <- classes relacionadas aos graficos e imagens
│        │     │
│        │     └── sfx                      <- classes relacionadas aos efeitos sonoros e musicas
│        │     
│        └── game                           <- arquivos relacionados ao funcionamento do jogo dungeon and wumpus em específico
│             ├── entitiesCharacters        <- classes relacionadas a todos os personagens
│             │
│             ├── entitiesTiles             <- classes relacionadas a estrutura do mapa (exemplo: paredes, escadas, plataformas)
│             │
│             ├── itens                     <- itens do jogo (exemplo: pocoes, chaves, tocha)
│             │
│             └── demais classes
│
└── bin                                     <- arquivos binarios gerados
    │
    └──mc322
    └── (...)                           <- estrutura igual a de src

```


## `data`

Todos os arquivos utilizados para contruir o mapa e as salas (dentro de layout) com a extensao csv.

> [Arquivos Data](https://github.com/LucasNP/Dungeon-and-Wumpus/tree/main/_data)


## `assets`

Todas as imagens e animaçoes separadas por pastas que definem as categorias: attacks para animaçoes de ataques e recebimento de dano; characters para as imagens e animacoes dos personagens jogaveis; enemies para as animações e imagens de inimigos; itens para animações e imagens de itens (como tochas, baus e poçoes); menu para animações e imagens do menu como mapa, pause/play e imagens de fim de jogo; tiles para animações e imagens de terrenos do jogo.

> [Arquivos Assets](https://github.com/LucasNP/Dungeon-and-Wumpus/tree/main/_assets)



## `sounds`

Qualquer mídia sonora usada no projeto como musicas (na pasta musics) e efeitos sonoros.

> [Arquivos Sounds](https://github.com/LucasNP/Dungeon-and-Wumpus/tree/main/_sounds)


## `src`

Projeto em Java, em Eclipse, incluindo todos os arquivos de dados e bibliotecas necessários para a sua execução.

> [Arquivos Java](https://github.com/LucasNP/Dungeon-and-Wumpus/tree/main/src/mc322)


## `bin`

Arquivos binarios ( Classes ) criados ao executar os códigos em src

> [Arquivos Java](https://github.com/LucasNP/Dungeon-and-Wumpus/tree/main/bin/mc322)

# Descrição Resumida do Jogo

Dungeons and Wumpus é um jogo estilo RPG tático de exploração, em que um time de quatro personagens explora baús em busca de chaves e itens para torna-los mais fortes; derrota inimigos e finalmente enfrenta o Wumpus em uma sala trancada.
Se o wumpus for derrotado o jogo acaba com sua vitória, mas se todos os personagens forem derrotados, você perde

# Equipe
* `Lucas Nogueira Roberto` - `182553`
* `Nícolas Hecker Silva` - `186132`

# Vídeos 

## Video da Prévia

> [![Watch the video](https://img.youtube.com/vi/NF305OLXkHI/hqdefault.jpg)](https://youtu.be/NF305OLXkHI)

## Vídeos do Jogo Final

> [![Watch the video](https://img.youtube.com/vi/Na2OdHFvV40/hqdefault.jpg)](https://youtu.be/Na2OdHFvV40)


# Slides

### Slides da Prévia
> [Previa](https://github.com/LucasNP/Dungeon-and-Wumpus/blob/main/img/slides_previa.pdf)

### Slides da Apresentação Final
> [Final](https://docs.google.com/presentation/d/12AGOCdr9TCZtNuKXdXyOH3rRsBixQjqbBebKOvMPd0Y/edit?usp=sharing)

# Como rodar o jogo ?

Se for de seu interesse compilar e rodar os arquivos fontes do jogo, atente-se, os arquivos estão preparados para rodarem em uma IDE como Eclipse para executar da linha de comando pode ser necessário alterar no arquivo src/mc322/GameMapTokens.java o path referente as pastas externas para que os arquivos binários sejam gerados no local certo

# Relatório de Evolução
* A evolução e execução do projeto pode ser dividida em 3 etapas: planejamento, execução e finalização.

   * Planejamento:  Primeiramente o projeto foi idealizado para assim termos objetivos esclarecidos, foi decidido o gênero do jogo, como ele poderia ser jogado, quais elementos poderiam estar presentes neste, e os seus diferenciais em relação a outros jogos parecidos, então buscamos inspiração em outras mídias e organizamos um brainstorm de ideias em documentos de texto. Com todos os conceitos em mãos foram desenhados e preparados quais seriam os cenários, os elementos deste, o design e a funcionalidade de cada personagem, os inimigos, os items e por fim estabelecemos uma personalidade para guiar a produção das músicas e resto dos Assets, com tudo em mão iniciamos o planjamento dos mecanismos internos e como cada coisa se comunicaria com a outra dentro do jogo, ainda de forma bem abstrata, posto isso começamos a execução.

   * Execução: Na execução procuramos ser o mais fiel possível ao que foi planejado, mas ao longo do desenvolvimento foram se alterando e melhorando alguns aspectos, utilizamos do Git para controle de versão e fomos desenvolvendo e expandindo mais nossa arquitetura, decidimos ao longo do projeto, para fins de aprendizado programar tudo, incluindo nossos metodos de renderização, do zero, assim dividimos o projeto em Engine e Game, onde o primeiro é referente a comportamentos gerais de um jogo, que poderiamos reutilizar em outros projetos e o segundo as funcionalidades especificas do trabalho

   * Finalização: Na finalização buscamos fazer ajustes finos ao projeto como um todo, fazendo testes no programa para localizar e resolver bugs e melhorando aspectos da jogabilidade para ficar mais agradável ao usuario.

# Destaques de Código

## Sistema de coordenadas isometricas


~~~java
    public abstract class LinearAlgebra{
          public static Pair<Integer, Integer> toIsometrica(Pair<Integer, Integer> p){
                int nx = p.getFirst() + p.getSecond();
                int ny = (p.getSecond() - p.getFirst())/2;

                Pair <Integer, Integer> np = Pair.of(nx, ny);
                return np;
          }
          
          public static Pair<Integer, Integer> toCartesianas(Pair<Integer, Integer> p){
                int ny = (p.getSecond()*2 + p.getFirst())/2;
                int nx = p.getFirst() - ny;

                Pair <Integer, Integer> np = Pair.of(nx, ny);
                return np;
          }

          ...
~~~

> A principal indentidade visual do jogo Dungeon & Wumpus é sua visual 2.5D, também conhecida como perspectiva isometrica, para alcançarmos esse visual e fazer o jogo ainda funcionar baseado em um sistema de grid carteseana nos utilizamos de uma transformação de bases para transitar entre um sistema de coordenadas carteseanas e isometricas e isso foi implementado na nossa classe propria de operações lineares.

## Busca em Largura


~~~java
      public static String solveMaze(char map[][],int iBeg,int jBeg, int iEnd, int jEnd) throws ImpossibleOriginOrDestiny // for Square maps
            , UnexpectedError
            {

            ...

                  //arrays of new points
                  ArrayList<Pair<Integer,Integer>> news = new ArrayList<Pair<Integer,Integer>>();
                  ArrayList<Pair<Integer,Integer>> newNews = new ArrayList<Pair<Integer,Integer>>();

                  //directions
                  int dirI[] = {1,0,0,-1};
                  int dirJ[] = {0,-1,1,0};

                  //add origin
                  newNews.add(Pair.of(iEnd,jEnd));


                  boolean running = true;

                  int counting = 0;
                  while(running && counting <map.length*map.length/2)
                  {
                        //pass all newnews points to new
                        news.clear();
                        for(int i = 0;i<newNews.size();i++)
                        {
                              news.add(newNews.get(i));
                        }
                        newNews.clear();

            ...

~~~

> A fim de realizar a mecânica principal de movimentos dos personagens que consiste em clicar com o mouse em um quadrado da dungeon e ele andar até lá no menor caminho e desviando de inimigos e paredes foi utiizado um algoritmo de BFS (busca em largura) em grid para resolver esse "labirinto", assim este é um destaque importante.


# Destaques de Pattern

## Renderer

> diagrama

### Código do Pattern
~~~java
    public class Renderer{

          private int pW, pH;
          private int[] p;

          public Renderer(GameContainer gc){
                this.pW = gc.getWidth();
                this.pH = gc.getHeight();
                this.p = ((DataBufferInt) gc.getWindow().getImage().getRaster().getDataBuffer()).getData();

          }

          public void clear(){
                for(int i = 0; i < p.length; i++){
                      p[i] = 0xff000000;
                }
          }

          public void setPixel(int x, int y, int value){
                if( (x < 0 || x >= pW || y < 0 || y >= pH) || value == 0xffff00ff) return;
                p[x + y*pW] = value;
          }


          // Bresenham’s Line Algorithm
          public void drawLine(Pair<Integer, Integer> a, Pair<Integer, Integer> b, int color){
          ...

          // Bresenham’s Circle Algorithm
          public void drawCirc(int xi, int yi, int r, int color){
          ...

          public void drawPolygon(ArrayList<Pair<Integer, Integer>> poly, int color){
          ...
~~~

> Pattern criado para padronizar operações da renderização de formas e imagens e spritesheets

## GameMapTokens

> diagrama

### Código do Pattern
~~~java
    public abstract class GameMapTokens{
          private static ImageTile image;
          private static int tileWidth = 64;
          private static int tileHeight = 64;

          private static String PNG = ".png";
          private static String CSV = ".csv";
          private static String WAV = ".wav";

          private static String ASSETS = "../_assets";
          private static String SOUNDS = "../_sounds";
          private static String DATA   = "../_data";
          private static String MUSICS = "musics";

          private static Map<String, ImageTile> mapTokens = new HashMap<>();

          private static String DIR_ATTACKS     = ASSETS + "/attacks/";
          private static String DIR_CHARACTERS = ASSETS + "/characters/";
          private static String DIR_ENEMIES    = ASSETS + "/enemies/";
          private static String DIR_ITENS      = ASSETS + "/itens/";
          private static String DIR_MENU       = ASSETS + "/menu/";

          ...
~~~

> Pattern criado para padronizar operações de acesso a elementos externos como sprites, sons, musica e arquivos de dados


## Menu

> diagrama

### Código do Pattern
~~~java
public class Menu implements BasicObject{

      GameManager game;
      String STATE;


      public Menu(GameManager game)
      {
            this.game = game;
            this.STATE = "game";
      }

      public void update(double dt) {

      }

      public void setState(String State)
      {
~~~

> Pattern criado para padronizar operações de mudança de estado do jogo (game, combat, victory, defeat)


# Conclusões e Trabalhos Futuros


É possivel afirmar que os objetos iniciais do trabalho foram obtidos com êxito com a criação de um jogo diverso de estrategia com personagens distintos e de atributos proprios que divertido de jogar mas ao mesmo tempo com uma dificuldade balanceada, as implementações de funções de baixo para renderizar objetos permitiu uma maior liberdade criativa para possibilitar animações, formas complexas e uso de spritesheet, acreditamos que no futuro isso permita a implementação de mais estados de jogo, sistema de particulas dinâmicas e maior fluidez nas animações. Planejamos implementar futuramente mais inimigos, areas, itens e bosses, além de uma história mais explicita e um sistema de dialogos para maior imersão do jogador no nosso jogo.


# Diagrama Geral do Projeto

> ![Diagrama Geral de Componentes](/img/diagram_global.png)

> No diagrama apresentado, podemos ver como as classes dentro das componentes se comunicam entre si e quais suas dependencias


## Diagrama da dungeon

> ![Diagrama Geral da Dungeon](/img/Dungeon.png)

## Classe da Room
> ![Diagrama mostrando a aquitetura interna geral da classe room](/img/Room.png)


## Diagrama do fluxo de turn
> ![Diagrama explicativo para o fluxo de turnos no jogo](/img/Turn.png)

# Detalhamento das Componentes 


## Componente entitiesCharacters


**Ficha Técnica**
item | detalhamento
----- | -----
Classe | `mc322.game.entitiesCharacters`
Autores | `Lucas Nogueira e Nicolas Hecker`
Interfaces e classes abstratas | `Heroes, Character, Enemys, Luna, Milo, Raju, Ze, Wumpus`


## Componente entitiesTiles


**Ficha Técnica**
item | detalhamento
----- | -----
Classe | `mc322.game.entitiesTiles`
Autores | `Lucas Nogueira e Nicolas Hecker`
Interfaces e classes abstratas | `Chest, Door, Ladder, Pillar, Platform, SafeZone, Torch`


## Componente itens


**Ficha Técnica**
item | detalhamento
----- | -----
Classe | `mc322.game.itens`
Autores | `Lucas Nogueira e Nicolas Hecker`
Interfaces e classes abstratas | `BasicObject, Entity, HealthPotion, Item, Key, ResistancePotion, StrengthPotion`



# Detalhamento das Interfaces e Classes Abstratas

### Interface AbstractGame

Interface responsavel pela generalização do controlador do fluxo do jogo

~~~java
public interface AbstractGame{
      public void update(GameContainer gc, double dt);
      public void renderer(GameContainer gc, Renderer r);
}
~~~

Método | Objetivo
-------| --------
`update` | Metodo para atualizar informações referentes ao game manager
`renderer` | Método para renderizar objetos pertencentes ao controlador do fluxo do jogo na tela


### Interface BasicObject

Interface responsavel pela generalização dos objeto mais básico de um espaço celular

~~~java
public interface BasicObject{
      public void update(double dt);
      public void renderer(Renderer r);
}
~~~

Método | Objetivo
-------| --------
`update` | Metodo para atualizar informações referentes ao objeto
`renderer` | Método para renderizar objeto na tela, usado para deixar ou não o objeto visivel e animar seus sprites também


### Abstract Class Entity

Classe abstrata responsavel pela generalização de entidades que interagem no jogo

~~~java
public abstract class Entity implements BasicObject{

      ...
      
      public abstract void toggleAnimation();
      public void setElevation(double newElevation)
      {
    	  this.elevation = newElevation;
      }
      public int getDirection()
      {
    	  return this.updateDir;
      }
      
      public void setPos(int i,int j)
      {
    	  this.i=i;
    	  this.j=j;
      }
      
      public Pair<Integer,Integer> getPos()
      {
    	  return Pair.of(this.i,this.j);
      }
    ...
~~~


Método | Objetivo
-------| --------
`toggleAnimation` | Metodo para desligar e ligar animação da entidade
`getDirection` | Metodo para pegar a direção da entidade
`setPos` | Metodo para setar a posição da entidade



# Plano de Exceções

## Diagrama da hierarquia de exceções

> ![Diagrama Geral de Componentes](/img/diagramaExec.png)

## Descrição das classes de exceção

`<Monte uma tabela descritiva seguindo o exemplo>:`

Classe | Descrição
----- | -----
ChangeRoomInvalidChar | Engloba todas as exceções de troca de sala
DoorSelected | Indica que uma porta foi selecionada
FullPlaceException | Engloba todas as exceções de posicionamento na mesma célula
ImpossibleOriginOrDestiny | Indica movimento para local inválido
NoEnemyHere | Indica que em uma sala não foi gerado inimigos
Victory | Indica que o Wumpus foi morto e o jogador ganhou o jogo
GameOver | Indica que o jogador perdeu todos os personagens

