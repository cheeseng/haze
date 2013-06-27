package com.amaseng.haze

import org.scalatest.selenium.WebBrowser
import org.openqa.selenium._
import htmlunit.HtmlUnitDriver
import collection.JavaConverters._

object HazeIndexParser extends WebBrowser {

  implicit val webDriver: WebDriver = new HtmlUnitDriver

  def main(args: Array[String]) {
    go to "http://www.doe.gov.my/apims"
    val tableData = 
      for (row <- findAll(xpath("//*[contains(@class,'table1')]//tr"))) yield {
        val cols = row.underlying.findElements(By.xpath(".//td")).asScala
        cols map { col => 
          col.getText
        }
      }
    tableData.next() // First row is header, and some warning messages will be printed
    println("####################################################")
    println("Extracted data: ")
    tableData.foreach { line => 
      println(line.mkString(", "))
    }
    println("####################################################")
  }

}
