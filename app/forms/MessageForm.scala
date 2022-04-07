package forms
//モデルではtitleはOption[String]だが、入力では必須のためString
case class MessageForm(id: Option[Long], title: String, body: String)