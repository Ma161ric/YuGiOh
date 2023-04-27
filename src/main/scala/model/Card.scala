package model

enum CardName(firstName: String):
  override def toString:String = firstName

  case roter extends CardName("Roter")
  case schwarzer extends CardName("Schwarzer")
  case blauer extends CardName("Blauer")
  case weisser extends CardName("Weisser")
  case boeser extends CardName("Böser")
  case guter extends CardName("Guter")
  case emptyName extends CardName("No")

enum CardLastName(lastName: String):
  override def toString:String = lastName

  case Drache extends CardLastName("Drache")
  case Magier extends CardLastName("Magier")
  case Hexer extends CardLastName("Hexer")
  case Gnom extends CardLastName("Gnom")
  case Reiter extends CardLastName("Reiter")
  case Krieger extends CardLastName("Krieger")
  case emptyLastName extends CardLastName("Card")


enum Card(firstName: CardName, lastName: CardLastName, atk: Int, defe: Int, position: String):
  override def toString:String = firstName.toString + lastName.toString

  def atkToString = atk.toString
  def defeToString = defe.toString
  def getFirstName = firstName.toString;
  def getLastName = lastName.toString;
  def getAtk = atk;
  def getDefe = defe;
  def getPosition = position;


  def generateDeckCards(): List[Card] = {
    val cardNames = List(CardName.roter, CardName.schwarzer, CardName.blauer, CardName.weisser, CardName.boeser, CardName.guter, CardName.emptyName)
    val cardLastNames = List(CardLastName.Drache, CardLastName.Magier, CardLastName.Hexer, CardLastName.Gnom, CardLastName.Krieger, CardLastName.Reiter, CardLastName.emptyLastName)
    val deckAtkList = (200 to 1000 by 20).take(36).toList
    val deckDefList = (300 to 1000 by 20).take(36).toList
    val deckPositionList = List.fill(36)("hand")
    
    for {
      cardName <- cardNames
      cardLastName <- cardLastNames
      atk <- deckAtkList
      defe <- deckDefList
      position <- deckPositionList
    } yield Card(cardName, cardLastName, atk, defe, position)
  }
  
  case empty extends Card(CardName.emptyName, CardLastName.emptyLastName, 0, 0, "hand")
