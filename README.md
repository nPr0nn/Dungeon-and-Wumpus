# Projeto Dungeon-and-Wumpus

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

as

## `data`

Todos os arquivos utilizados para contruir o mapa e as salas (dentro de layout) com a extensao csv.

> [Arquivos Data](https://github.com/LucasNP/Dungeon-and-Wumpus/tree/main/_data)


## `assets`

Todas as imagens e animaçoes separadas por pastas que definem as categorias: attacks para animaçoes de ataques e recebimento de dano; characters para as imagens e animacoes dos personagens jogaveis; enemies para as animações e imagens de inimigos; itens para animações e imagens de itens (como tochas, baus e poçoes); menu para animações e imagens do menu como mapa, pause/play e imagens de fim de jogo; tiles para animações e imagens de terrenos do jogo.

> [Arquivos Assets](https://github.com/LucasNP/Dungeon-and-Wumpus/tree/main/_assets)



## `sounds`

Qualquer mídia sonora usada no projeto como musicas (na pasta musics) e efeitos sonoros.

> [Arquivos Sounds](https://github.com/LucasNP/Dungeon-and-Wumpus/tree/main/src)


## `src`

Projeto em Java, em Eclipse, incluindo todos os arquivos de dados e bibliotecas necessários para a sua execução.

> [Arquivos Java](https://github.com/LucasNP/Dungeon-and-Wumpus/tree/main/src)


## `bin`

Arquivos binarios ( Classes ) criados ao executar os códigos em src

> [Arquivos Java](https://github.com/LucasNP/Dungeon-and-Wumpus/tree/main/bin)

# Descrição Resumida do Jogo

> Dungeons and Wumpus é um jogo estilo RPG de exploração, em que um time de quatro personagens explora baús em busca de chaves e itens para torna-los mais fortes; derrota inimigos e finalmente enfrenta o Wumpus em uma sala trancada.
> Se o wumpus for derrotado o jogo acaba com sua vitória, mas se todos os personagens forem derrotados, você perde

# Equipe
* `Lucas Nogueira Roberto`
* `Nícolas Hecker Silva` - `186132`

## Vídeo da Prévia
> [Link](https://www.youtube.com/watch?v=NF305OLXkHI)

# Vídeos do Jogo
>[Link](link)

# Slides do Projeto

## Slides da Prévia
>[Link](link)

## Slides da Apresentação Final
>[Link](link)

## Relatório de Evolução

> <Relatório de evolução, descrevendo as evoluções do design do projeto, dificuldades enfrentadas, mudanças de rumo, melhorias e lições aprendidas. Referências aos diagramas e recortes de mudanças são bem-vindos.>

# Destaques de Código

> <Escolha trechos relevantes e/ou de destaque do seu código. Apresente um recorte (você pode usar reticências para remover partes menos importantes). Veja como foi usado o highlight de Java para o código.>

~~~java
// Recorte do seu código
public void algoInteressante(…) {
   …
   trechoInteressante = 100;
}
~~~

# Destaques de Pattern
`<Destaque de patterns adotados pela equipe. Sugestão de estrutura:>`

## Diagrama do Pattern
`<Diagrama do pattern dentro do contexto da aplicação.>`

## Código do Pattern
~~~java
// Recorte do código do pattern seguindo as mesmas diretrizes de outros destaques
public void algoInteressante(…) {
   …
   trechoInteressante = 100;
}
~~~

> <Explicação de como o pattern foi adotado e quais suas vantagens, referenciando o diagrama.>

# Conclusões e Trabalhos Futuros

> <Apresente aqui as conclusões do projeto e propostas de trabalho futuro. Esta é a oportunidade em que você pode indicar melhorias no projeto a partir de lições aprendidas e conhecimentos adquiridos durante a realização do projeto, mas que não puderam ser implementadas por questões de tempo. Por exemplo, há design patterns aprendidos no final do curso que provavelmente não puderam ser implementados no jogo -- este é o espaço onde você pode apresentar como aplicaria o pattern no futuro para melhorar o jogo.>

# Documentação dos Componentes

O vídeo a seguir apresenta um detalhamento de um projeto baseado em componentes:

[![Projeto baseado em Componentes](http://img.youtube.com/vi/1LcSghlin6o/0.jpg)](https://youtu.be/1LcSghlin6o)

# Diagramas

## Diagrama Geral do Projeto

>[Link](link)

> <Faça uma breve descrição do diagrama.>

## Diagrama Geral de Componentes

### Exemplo 1

Este é o diagrama compondo componentes para análise:

![Diagrama Analise](diagrama-componentes-analise.png)

## Componente `<Nome do Componente>`

> <Resumo do papel do componente e serviços que ele oferece.>

![Componente](diagrama-componente.png)

**Ficha Técnica**
item | detalhamento
----- | -----
Classe | `<caminho completo da classe com pacotes>` <br> Exemplo: `pt.c08componentes.s20catalog.s10ds.DataSetComponent`
Autores | `<nome dos membros que criaram o componente>`
Interfaces | `<listagem das interfaces do componente>`

### Interfaces

Interfaces associadas a esse componente:

![Diagrama Interfaces](diagrama-interfaces.png)

Interface agregadora do componente em Java:

~~~java
public interface IDataSet extends ITableProducer, IDataSetProperties {
}
~~~

## Detalhamento das Interfaces

### Interface `<nome da interface>`

`<Resumo do papel da interface.>`

~~~
<Interface em Java.>
~~~

Método | Objetivo
-------| --------
`<id do método em Java>` | `<objetivo do método e descrição dos parâmetros>`

## Exemplo:

### Interface `ITableProducer`

Interface provida por qualquer fonte de dados que os forneça na forma de uma tabela.

~~~java
public interface ITableProducer {
  String[] requestAttributes();
  String[][] requestInstances();
}
~~~

Método | Objetivo
-------| --------
`requestAttributes` | Retorna um vetor com o nome de todos os atributos (colunas) da tabela.
`requestInstances` | Retorna uma matriz em que cada linha representa uma instância e cada coluna o valor do respectivo atributo (a ordem dos atributos é a mesma daquela fornecida por `requestAttributes`.

### Interface `IDataSetProperties`

Define o recurso (usualmente o caminho para um arquivo em disco) que é a fonte de dados.

~~~java
public interface IDataSetProperties {
  public String getDataSource();
  public void setDataSource(String dataSource);
}
~~~

Método | Objetivo
-------| --------
`getDataSource` | Retorna o caminho da fonte de dados.
`setDataSource` | Define o caminho da fonte de dados, informado através do parâmetro `dataSource`.

# Plano de Exceções

## Diagrama da hierarquia de exceções
`<Elabore um diagrama com a hierarquia de exceções como detalhado abaixo>`

![Hierarquia Exceções](exception-hierarchy.png)

## Descrição das classes de exceção

`<Monte uma tabela descritiva seguindo o exemplo>:`

Classe | Descrição
----- | -----
DivisaoInvalida | Engloba todas as exceções de divisões não aceitas.
DivisaoInutil | Indica que a divisão por 1 é inútil.
DivisaoNaoInteira | Indica uma divisão não inteira.
