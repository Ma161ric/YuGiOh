package model

enum CardName(firstName: String):
  override def toString:String = firstName

  case roter extends CardName("Roter")
  case schwarzer extends CardName("Schwarzer")
  case blauer extends CardName("Blauer")
  case weisser extends CardName("Weisser")
  case boeser extends CardName("Böser")
  case guter extends CardName("Guter")
  case empty extends CardName("No")

enum CardLastName(lastName: String):
  override def toString:String = lastName

  case Drache extends CardLastName("Drache")
  case Magier extends CardLastName("Magier")
  case Hexer extends CardLastName("Hexer")
  case Gnom extends CardLastName("Gnom")
  case Reiter extends CardLastName("Reiter")
  case Krieger extends CardLastName("Krieger")
  case empty extends CardLastName("Card")


enum Card(firstName: CardName, lastName: CardLastName, atk: Int, defe: Int, position: String):
  override def toString:String = firstName.toString + lastName.toString

  def atkToString = atk.toString
  def defeToString = defe.toString
  def getFirstName = firstName.toString;
  def getLastName = lastName.toString;
  def getAtk = atk;
  def getDefe = defe;
  def getPosition = position;

  val cardNames = List(CardName.roter, CardName.schwarzer, CardName.blauer, CardName.weisser, CardName.boeser, CardName.guter, CardName.empty)
  val cardLastNames = List(CardLastName.Drache, CardLastName.Magier, CardLastName.Hexer, CardLastName.Gnom, CardLastName.Krieger, CardLastName.Reiter, CardLastName.empty)

  val deckNamesList: List[(CardName, CardLastName)] = 
    for {
      cardName <- cardNames
      cardLastName <- cardLastNames
    } yield {
      (cardName, cardLastName)
    }

  case roterDrache extends Card(CardName.roter,CardLastName.d, 500, 200, "hand")
  case schwarzerDrache extends Card(CardName.schwarzer,"Drache", 450, 200, "hand")
  case blauerDrache extends Card(CardName.blauer,"Drache", 400, 200, "hand")
  case weisserDrache extends Card(CardName.weisser,"Drache", 500, 300, "hand")
  case emptyCard extends Card("No","Card",0,0, "hand")
