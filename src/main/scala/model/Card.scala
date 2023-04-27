package model

enum Card(firstName: String, lastName: String, atk: Int, defe: Int, position: String):
  override def toString:String = firstName + lastName

  def atkToString = atk.toString
  def defeToString = defe.toString
  def getFirstName = firstName;
  def getLastName = lastName;
  def getAtk = atk;
  def getDefe = defe;
  def getPosition = position;


  case roterDrache extends Card("Roter","Drache", 500, 200, "hand")
  case schwarzerDrache extends Card("Schwarzer","Drache", 450, 200, "hand")
  case blauerDrache extends Card("Blauer","Drache", 400, 200, "hand")
  case weisserDrache extends Card("Weisser","Drache", 500, 300, "hand")
  case emptyCard extends Card("No","Card",0,0, "hand")
