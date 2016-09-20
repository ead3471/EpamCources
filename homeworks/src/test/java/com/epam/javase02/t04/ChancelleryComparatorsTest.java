package com.epam.javase02.t04;

import com.epam.javase02.t02.chancellery.Chancellery;
import com.epam.javase02.t03.ChancellerySets;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Freemind on 2016-09-20.
 */
public class ChancelleryComparatorsTest {
    List<Chancellery> chancellerySet= ChancellerySets.createRandomSet();
    @Test
    public void priceComparatorTest(){
        chancellerySet.sort(ChancelleryComparators.getByPriceComparator());

        for(int i=0;i<chancellerySet.size()-1;i++)
        {
            assertThat(chancellerySet.get(i).getPrice()<=chancellerySet.get(i+1).getPrice(),is(true));
        }
    }

    @Test
    public void byNameComparatorTest() {

        chancellerySet.sort(ChancelleryComparators.getByNameComparator());
        for(int i=0;i<chancellerySet.size()-1;i++)
        {
            assertThat(chancellerySet.get(i).getName().compareTo(chancellerySet.get(i+1).getName())<=0,is(true));
        }
    }

    @Test
    public void byPriceAndNameComparatorTest(){
        chancellerySet.sort(ChancelleryComparators.getByNameAndPriceComparator());
        for(int i=0;i<chancellerySet.size()-1;i++)
        {
            assertThat(chancellerySet.get(i).getName().compareTo(chancellerySet.get(i+1).getName())<=0,is(true));
            if(chancellerySet.get(i).getName().compareTo(chancellerySet.get(i+1).getName())==0)
                assertThat(chancellerySet.get(i).getPrice()<=chancellerySet.get(i+1).getPrice(),is(true));
        }
    }


}