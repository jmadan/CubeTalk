package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Iterator;
import java.util.List;

@Entity
public class CubeRating extends Model {

    @ManyToOne
    public AnonymousUser anonymousUser;

    @ManyToOne
    public Company company;

    public Integer questionID;
    public Integer rating;

    public CubeRating(AnonymousUser anonymousUser, Company company, Integer questionID, Integer rating){
        this.anonymousUser = anonymousUser;
        this.company = company;
        this.questionID = questionID;
        this.rating = rating;
    }

    public static String getGraphData(List<CubeRating> cubeRatings) {
        Integer verySatisfied=0;
        Integer satisfied=0;
        Integer neutral=0;
        Integer disatisfied=0;
        Integer veryDisatisfied=0;
        String graphData=null;
        Iterator<CubeRating> iterator = cubeRatings.iterator();
        while(iterator.hasNext()){
            CubeRating cubeRating = (CubeRating)iterator.next();
            switch (cubeRating.rating.intValue()){
                case 1:
                    veryDisatisfied += cubeRating.rating;
                    break;
                case 2:
                    disatisfied += cubeRating.rating;
                    break;
                case 3:
                    neutral += cubeRating.rating;
                    break;
                case 4:
                    satisfied += cubeRating.rating;
                    break;
                case 5:
                    verySatisfied += cubeRating.rating;
                    break;
            }
        }
        graphData = veryDisatisfied.toString()+","+disatisfied.toString()+","+neutral.toString()+","+satisfied.toString()+","+verySatisfied.toString();

        String graphJs="<script>\n" +
                "    window.onload = function ()\n" +
                "    {\n" +
                "        // The data to be shown on the Pie chart\n" +
                "        var data = ["+veryDisatisfied.toString()+","+ disatisfied.toString()+","+neutral.toString()+","+satisfied.toString()+","+verySatisfied.toString()+"];\n" +
                "    \n" +
                "        // Create the Pie chart. The arguments are the canvas ID and the data to be shown.\n" +
                "        var pie = new RGraph.Pie('myPie', data);\n" +
                "\n" +
                "        // Configure the chart to look as you want.\n" +
                "        pie.Set('chart.colors', ['red','maroon','yellow','blue','green']);\n" +
                "        pie.Set('chart.title', 'What Employees say');\n\n" +
                "        pie.Set('chart.title.color', '#9FA85D');\n" +
                "        pie.Set('chart.gutter.top', 40);\n" +
                "        pie.Set('chart.gutter.right', 10);\n" +
                "        pie.Set('chart.text.size', 10);\n" +
                "        pie.Set('chart.text.font', 'Trebuchet MS');\n" +
                "        pie.Set('chart.text.color', 'white');\n" +
//                "        pie.Set('chart.stroke', 'white');\n" +
                "        pie.Set('chart.key', ['I Wanna Leave now', 'I am Looking', 'I am Used to this', 'I Like It', 'This is Great']);\n" +
                "        pie.Set('chart.key.halign', 'right');\n" +
                "        pie.Set('chart.exploded', [10,10,10,10,10]);\n" +
                "        pie.Set('chart.linewidth', '2');\n" +
                "        pie.Set('chart.strokestyle', 'white');\n" +
                "        pie.Set('chart.shadow', true);\n" +
                "        pie.Set('chart.shadow.offsetx', 0);\n" +
                "        pie.Set('chart.shadow.offsety', 0);\n" +
                "        pie.Set('chart.shadow.blur', 25);\n" +
                "        \n" +
                "        // Call the .Draw() chart to draw the Pie chart.\n" +
                "        pie.Draw();\n" +
                "    }\n" +
                "</script>";
        return graphJs;
    }
}
