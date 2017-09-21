import com.sun.javafx.image.BytePixelSetter;

/**
 * Created by Antin on 9/8/2017.
 */
public class Histogram
{
    protected long[] bins;
    protected int min, max, range;
    protected int numBins;

    public Histogram(int max, int min, int numBins)
    {
        this.max = max;
        this.min = min;
        this.numBins = numBins;
        bins = new long[numBins];
        range = max - min + 1;
    }

    public void add(int num)
    {
        int bin = (int) Math.floor(((num - min) * 1.0 / range) * numBins);
        bins[bin]++;
    }

    public int absDifference(Histogram histogram)
    {
        int sum = 0;
        if (histogram.min == min && histogram.max == max && histogram.numBins == numBins)
            for (int i = 0; i < bins.length; i++)
                sum += (int) Math.abs(bins[i] - histogram.bins[i]);

        return sum;
    }

    @Override
    public String toString()
    {
        String out = String.format("{Min: %d, Max: %d, # Bins: %d, Values: ", min, max, numBins);
        for (int i = 0; i < bins.length; i++)
            out += bins[i] + ", ";

        out = out.substring(0, out.length() - 2);
        out += "}";
        return out;
    }
}
