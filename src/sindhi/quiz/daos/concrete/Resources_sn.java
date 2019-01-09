package sindhi.quiz.daos.concrete;


import java.util.ListResourceBundle;

public class Resources_sn extends ListResourceBundle
{
    private static final Object[][] CONTENTS = new Object[][] {
                                  
    	{ "CenturyPattern", "%n %u" },
        { "CenturyFuturePrefix", "۾ " },
        { "CenturyFutureSuffix", "" },
        { "CenturyPastPrefix", "اڳ۾ " },
        { "CenturyPastSuffix", "" },
        { "CenturyName", "Jahrhundert" },
        { "CenturyPluralName", "Jahrhunderten" }
        //...
                                  
    };
    

    @Override
    protected Object[][] getContents()
    {
        return CONTENTS;
    }
}