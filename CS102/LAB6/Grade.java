/**
 * This is Grade class.
 * Each student has several exam grades. Each grade has name, weight and point.
 * 
 * @author ECE SESEN
 * @version 08.05.2024
 * 
 */

public class Grade 
{
    //Instance variables
    private String examName;
    private float weight;
    private float points;

    //Constructor
    public Grade(String name, float w, float p)
    {
        this.examName = name;
        this.weight = w;
        this.points = p;
    }

    /**
     * Put new weight in previous one (update it)
     * @param newW weight
     */
    public void updateWeight(float newW)
    {
        weight = newW;
    }

    /**
     * Put new point in previous one (update it)
     * @param newP point
     */
    public void updatePoint(float newP)
    {
        points = newP;
    }

    /**
     * Get exam name as string
     * @return exam name
     */
    public String getExamName()
    {
        return examName;
    }

    /**
     * Get exam weight
     * @return weight
     */
    public float getWeight()
    {
        return weight;
    }
    
    /**
     * Get point
     * @return point
     */
    public float getPoints()
    {
        return points;
    }
}
