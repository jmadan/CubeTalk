package models;

import org.dom4j.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.net.URL;

public class RSSReader {

        private static RSSReader instance = null;
        
        public RSSReader() {}
        
        public void writeNews() {
            try {
            
                DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
//                URL u = new URL("http://pratyush.in/feed/rss"); // your feed url
                URL u = new URL("http://newsrss.bbc.co.uk/rss/newsonline_world_edition/front_page/rss.xml"); // your feed url

                Document doc = builder.parse(u.openStream());
                
                NodeList nodes = doc.getElementsByTagName("item");
                
                for(int i=0;i<nodes.getLength();i++) {
                
                Element element = (Element)nodes.item(i);
                System.out.println("Title: " + getElementValue(element,"title"));
                System.out.println("Link: " + getElementValue(element,"link"));
                System.out.println("Publish Date: " + getElementValue(element,"pubDate"));
                System.out.println("author: " + getElementValue(element,"dc:creator"));
                System.out.println("comments: " + getElementValue(element,"wfw:comment"));
                System.out.println("description: " + getElementValue(element,"description"));
                System.out.println();
                }//for
            }//try
            catch(Exception ex) {
                ex.printStackTrace();
            }
        
        }
        
        private String getCharacterDataFromElement(Element e) {
            try {
                Node child = e.getFirstChild();
                if(child instanceof CharacterData) {
                    CharacterData cd = (CharacterData) child;
//                    return cd.getData();
                    return cd.getStringValue();
                }
            }
            catch(Exception ex) {
        
            }
            return "";
        } //private String getCharacterDataFromElement
        
        protected float getFloat(String value) {
          if(value != null && !value.equals("")) {
             return Float.parseFloat(value);
            }
            return 0;
        }
        
        protected String getElementValue(Element parent,String label) {
            return getCharacterDataFromElement((Element)parent.getElementsByTagName(label).item(0));
        }
        
//        public static void main(String[] args) {
//            RSSReader reader = RSSReader.getInstance();
//            reader.writeNews();
//        }

        public void readRss(){

            try {
                    SAXParserFactory factory = SAXParserFactory.newInstance();
                    SAXParser saxParser = factory.newSAXParser();

                    DefaultHandler handler = new DefaultHandler() {

                        boolean bfname = false;
                        boolean blname = false;
                        boolean bnname = false;
                        boolean bsalary = false;

                        public void startElement(String uri, String localName,String qName,
                                    Attributes attributes) throws SAXException {

                            System.out.println("Start Element :" + qName);

                            if (qName.equalsIgnoreCase("title")) {
                                bfname = true;
                            }

                            if (qName.equalsIgnoreCase("link")) {
                                blname = true;
                            }

                            if (qName.equalsIgnoreCase("pubDate")) {
                                bnname = true;
                            }

                            if (qName.equalsIgnoreCase("description")) {
                                bsalary = true;
                            }

                        }

                        public void endElement(String uri, String localName,
                            String qName) throws SAXException {

                            System.out.println("End Element :" + qName);

                        }

                        public void characters(char ch[], int start, int length) throws SAXException {

                            if (bfname) {
                                System.out.println("Title : " + new String(ch, start, length));
                                bfname = false;
                            }

                            if (blname) {
                                System.out.println("Link : " + new String(ch, start, length));
                                blname = false;
                            }

                            if (bnname) {
                                System.out.println("Publish Date : " + new String(ch, start, length));
                                bnname = false;
                            }

                            if (bsalary) {
                                System.out.println("Description : " + new String(ch, start, length));
                                bsalary = false;
                            }

                        }

                     };

                     saxParser.parse("http://newsrss.bbc.co.uk/rss/newsonline_world_edition/front_page/rss.xml", handler);

                     }
            catch (Exception e) {
                e.printStackTrace();
            }

        }
}