import java.util.*;
import java.io.*;
public class CofinFin {

    /// THIS IS THE TEMPLATE YOU SHOULD USE FOR THE TEAM PROJECT
    /*


Team members: Sage Hamer, Anne Turmel, Makayla Boyko, Cooper Duncan



Replace the stub code for each of the constructors and methods given below with
correct code.

NOTE, TESTING FOR THE ERROR CONDITIONS IS WORTH 2 POINTS EACH AND GIVING THE 
CORRECT ERROR MESSAGE IS ALSO WORTH 2 POINTS EACH.


     */
    private static final long
    RAND_SEED = 0L;

    private static final Random
    rng = new Random(RAND_SEED);

    private static final int
    Max = Integer.MAX_VALUE,
    MaxDiv2 = Max/2;

    private static CofinFin emT;

    private boolean isComplemented;
    private TreeSet<Integer> theSet; // should never be null, even if
    // the set is empty

    /*

     This class implements an instance of a subset of the natural numbers,
     { 0, 1, 2, ... }, that is either finite or cofinite(that is, its complement
     with respect to the natural numbers is theSet) according to the following
     scheme.

     There are two mutually exclusive cases

     Case I: the represented set is finite

     isComplemented is false

     theSet has exactly the members of the set which are ordered ascending in
     the tree.

     Examples

     1. empty set 
        isComplemented is false 
        theSet is empty, BUT NOT NULL

     2. { 0, 2, 4 }
        isComplemented is false
        theSet has the values { 0, 2, 4 }


     Case II: the represented is cofinite

     isComplemented is true and theSet has all the values that are NOT in the
     represented value.

     Below we use N to stand for the set of natural numbers { 0, 1, 2, 3, ... }

     Examples

     1. N = ( 0, 1, 2, 3, ... }
        isComplemented is true
        theSet is empty, BUT NOT NULL

     2. { 1, 3, 5, 6, 7, ... }
        isComplemented is true
        theSet has the values { 0, 2, 4 }

     class invariant:
        theSet is not null and never contains a value < 0

     Constructors should establish the class invariant and mutators 
     should preserve it.

     */

    // constructors

    // I WILL CODE THIS ONE SO I CAN USE IT BELOW
    public CofinFin() {
        // constructs the rep of the empty set;
        // isComplemented is initialized to false by default
        theSet = new TreeSet<Integer>();

    }

    public CofinFin(boolean comp, int n) { // DONE

        theSet = new TreeSet<Integer>(); // initializes to an empty set
        isComplemented = comp;

        if(n >= 0) {
            theSet.add(n);
        }

    }

    public CofinFin(boolean comp, int[] A) { // DONE

        theSet = new TreeSet<Integer>(); // initializes to an empty set
        isComplemented = comp; // sets complement status

        if(A != null) {
            for(int i = 0; i < A.length; i++)
                if(A[i] >= 0)
                    theSet.add(A[i]);
        }

    }

    // mutators

    public void remove(int n) {
        //removes element n from represented set
        //if theSet is a complement and n >= 0, 
        //remove element by adding to theSet
        //if theSet is not a complement, 
        //remove element from theSet

        if(isComplemented && n >= 0) {
            theSet.add(n);
        }
        else {
           /// hmmm... n < 0? not a problem if it is not in it
            theSet.remove(n);
        }

    }

    public void add(int n) {
        //add element n to represented set
        //if theSet is a complement, 
        //add element by removing from theSet
        //if theSet is not a complement, and n >= 0, 
        //add element to theSet

        if(n >= 0) {
            if(!isComplemented) {
                theSet.add(n);
            }
            else {
                theSet.remove(n);
            }
        }
    }

    // operations

    public CofinFin union(CofinFin other) throws Exception {
        //calculates the union of two sets, this and other

        if(other == null)
            throw new Exception("Invalid call to union: other is null");

        CofinFin result = new CofinFin();
        
/// can use || to set the isComplemented data member

        if(!this.isComplemented && !other.isComplemented) { // both false
            result.theSet.addAll(this.theSet);
            result.theSet.addAll(other.theSet);
        }
        else if(this.isComplemented && !other.isComplemented) { // this true other false
            result.isComplemented = true;
            result.theSet.addAll(this.theSet);
            result.theSet.removeAll(other.theSet);
        }
        else if(!this.isComplemented && other.isComplemented) { // this false other true
            result.isComplemented = true;
            result.theSet.addAll(other.theSet);
            result.theSet.removeAll(this.theSet);
        }
        else { // both true
            result.isComplemented = true;
            result.theSet.addAll(this.theSet);
            result.theSet.retainAll(other.theSet);
        }

        return result;

    }

    public CofinFin intersect(CofinFin other) throws Exception {
        //performs the intersection of two sets, this and other
        //removes this or other from result set
        //if one is complemented and the other isn't

        if(other == null)
            throw new Exception("Invalid call to intersect: other is null");

        CofinFin result = new CofinFin();

        if(!this.isComplemented && !other.isComplemented) { // both false
            result.theSet.addAll(this.theSet);
            result.theSet.retainAll(other.theSet);
        }
        else if(this.isComplemented && !other.isComplemented) { // this true other false
            result.theSet.addAll(other.theSet);
            result.theSet.removeAll(this.theSet);
        }
        else if(!this.isComplemented && other.isComplemented) { // this false other true
            result.theSet.addAll(this.theSet);
            result.theSet.removeAll(other.theSet);
        }
        else { // both true
            result.isComplemented = true;
            result.theSet.addAll(this.theSet);
            result.theSet.addAll(other.theSet);
        }
        return result;

    }

    public CofinFin complement() {
        //returns a complement of theSet this
        //simply creates a new set with this's values in it
        //and sets isComplemented to the opposite of this's isComplemented value

        CofinFin result = new CofinFin();

        result.theSet.addAll(this.theSet);
        result.isComplemented = !this.isComplemented;

        return result;
    }

    public boolean isIn(int n) throws Exception {
        //checks if element n in represented set
        //checks if element n in represented set, or in complement
        //returns accordingly based on whether theSet is a complement
        //throws an exception if n < 0

        if(n < 0) 
            throw new Exception("Invalid call to isIn: n < 0");


/// very good!


        return theSet.contains(n) ^ isComplemented;

    }


    public String toString() {
        /*
         If isComplemented is false if the set is empty returns the string "{}" else
         returns the string "{ v1, v2, ... , vk }" where the vi are the distinct
         k values in the set sorted in increasing order For example, { 0, 2, 3 }
         should return the string "{ 0, 2, 3 }" else returns the string CMPx
         where x is the string for the set if isComplemented were false, for
         example, all the natural numbers would have the string "CMP{}", the set
         that is all natural numbers except { 1, 3, 5 }, would have the string
         "CMP{ 1, 3, 5 }", etc.

         */

        boolean notEmpt = !theSet.isEmpty();

        StringBuffer bf = new StringBuffer();

        if (isComplemented)
            bf.append("CMP");

        bf.append('{');
        if (notEmpt){
            bf.append(' ');

            Iterator<Integer> iter = theSet.iterator();

            while (iter.hasNext()) {
                bf.append(iter.next().toString());
                if (iter.hasNext())
                    bf.append(", ");
            }
            bf.append(' ');
        }

        bf.append('}');

        return bf.toString();
    }

    public boolean equals(CofinFin other) throws Exception {

        // true when ((biconditional of this.cmp and other.cmp) && this.equals(other)) == true
        // biconditional is logically equivalent to the negation of the XOR

        if(other == null)
            throw new Exception("Invalid call to equals: other is null");
/// just ==;  !(p == q) iff p ^ q; ^ functions like !=
        return !(this.isComplemented ^ other.isComplemented) && (this.theSet.equals(other.theSet));

    }


    public boolean isSubsetOf(CofinFin other) throws Exception {
        /*Returns true if this is subset of other
        Returns false if this is not subset of other,
        especially if this is a complement and other is not
        Then this can't be subset of other,
        because the set this represents has more values in it than other,
        because the set this represents has infinite values in it, 
        and other has a finite number of values
         */

        if(other == null) 
            throw new Exception("Invalid call to isSubsetOf: other is null");


        if(!this.isComplemented && !other.isComplemented) { // both false
            if(other.theSet.containsAll(this.theSet)) 
                return true;
        }
        else if(!this.isComplemented && other.isComplemented) { // this false other true
            // If any elements of this are in other, then it will not be a subset
            // Therefore true when the intersection of this.theSet and other.theSet is empty

            CofinFin temp = new CofinFin();
            temp.theSet.addAll(this.theSet);
            temp.theSet.retainAll(other.theSet); // Identical to the regular intersection method
            if(temp.theSet.isEmpty()) 
                return true;
        }
        else if(this.isComplemented && other.isComplemented){ // both true
            if(this.theSet.containsAll(other.theSet))
                return true;
        }

        return false;
    }


    public int maximum() throws Exception{
        //finds and returns maximum value of theSet this
        //just gets last value of this, 
        //because values already in order, so last one will be maximum

        if(this.isComplemented)
            throw new Exception("Invalid call to maximum: receiver is cofinite");
        if(this.theSet.isEmpty())
            throw new Exception("Invalid call to maximum: receiver is empty");

        return this.theSet.last();

    }

    public int minimum() throws Exception{
        /*finds and returns minimum value of theSet this
        if this.isComplemented is false, just gets first value of this
        because values already in order, so first value will be minimum
        if this stores the complement of a set, 
        starts from 0, finds smallest value NOT in this's stored values
        because this would be storing values that aren't actually in the set
        so any valid value that wouldn't be in this would be in the actual set
         */


        if(this.theSet.isEmpty() && !this.isComplemented)
            throw new Exception("Invalid call to minimum: receiver is empty");

        if(!this.isComplemented)
            return this.theSet.first();

        else{
            int i = 0;
            while(this.theSet.contains(i)) { // needs to find the lowest value that is not in the set.
                i++;
            }
            return i;
        }


    }

    private static  // so they will all be initialized to null
    CofinFin
    // I don't use l for an identifier
    a,b,c,d,e,f,g,h,i,j,k,m,n,o,p,q,r,s,t,u,v,w,x,y,z,

    aa, ab, ac, ad, ae, af, ag, ah, ai, aj,ak, al, am, an,
    ao, ap, aq, ar, as, at, au, av, aw, ax, ay, az,

    ba, bb, bc, bd, be, bf, bg, bh, bi, bj, bk, bl, bm, bn,
    bo, bp, bq, br, bs, bt, bu, bv, bw, bx, by, bz,

    cc, dd, ee,

    newA, newB, newC;  // 25 + 52 + 6 = 83 variables





    //  the driver tests the methods
    public static void main (String[] args)throws Exception{

        boolean res;

        int ii, jj, kk;

        int [][] 
                arrayConstants = {
                        { -1, -2, -3, -4},
                        { 0, 1, 2, 3},
                        {-1, 0, -2, 1, -3, 2, -4}, 
                        { 2, 3, 5, 7 },
                        { 1, 3, 5, 7 },
                        { 1, 2, 3, 4 },
                        { 0, 1, 5, 6, 7, 10, 11, 13},
                        { 2, 3, 6, 8, 9 },
                        { 2, 4, 6, 8, 10, 13 },
                        { 13, 15, 17, 19, 21},
                        { 3, 5, 7, 11},
                        { 1, 2, 3, 7 },
                        { 10, 30, 50, 70},
                        { 10, 20, 30, 70  },
                        { 0, 1, 4, 5, 6, 7, 9},
                        { 0, 1, 4, 5, 6, 7, 9, 10},
                        { 8, 9, 11, 12 },
                        { 0, 1, 4, 5, 6, 7, 8, 10, 11, 12},
                        {100, 200, 300, 400, 500, 1000},
                        { 0, 2, 4, 6, 8, 10 ,12, 14 },
                        { 1, 3, 5, 7, 9, 11, 13, 15, 17 },
                        new int[0],
                        null,
                        { -1 },
                        { 0 },
                        { 100 },
                        {}
        };


        CofinFin[]
                fromStaticVariables = {

                        a,b,c,d,e,f,g,h,i,j,k,m,n,o,p,q,r,s,t,u,v,w,x,y,z,

                        aa, ab, ac, ad, ae, af, ag, ah, ai, aj,ak, al, am, an,
                        ao, ap, aq, ar, as, at, au, av, aw, ax, ay, az,

                        ba, bb, bc, bd, be, bf, bg, bh, bi, bj, bk, bl, bm, bn,
                        bo, bp, bq, br, bs, bt, bu, bv, bw, bx, by, bz,

                        cc, dd, ee,

                        newA, newB, newC} ,
                // 25 + 52 + 6 = 83 variables

                allTestCases = new CofinFin[arrayConstants.length * 2 + fromStaticVariables.length];


        ////////////////  SIMPLE CONSTRUCTOR TESTS

        System.out.println("Simple Constructor Tests.\n");

        a = new CofinFin(false, -1);
        System.out.println("a = " + a);
        b = new CofinFin(true, -2);
        System.out.println("b = " + b);
        c = new CofinFin(false, 1000);
        System.out.println("c = " + c);
        d = new CofinFin(true,1234);
        System.out.println("d = " + d);
        e = new CofinFin(false, 0);
        System.out.println("e = " + e);
        f = new CofinFin(true, 0);
        System.out.println("f = " + f);
        g = new CofinFin(false, 1);
        System.out.println("g = " + g);
        h = new CofinFin(true, 1);
        System.out.println("h = " + h);
        i = new CofinFin(false, Integer.MAX_VALUE);
        System.out.println("i = " + i);



        ////////////////  ARRAY CONSTRUCTOR TESTS

        System.out.println("\nArray Constructor Tests\n");

        for (int i = 0; i < arrayConstants.length * 2; i++){
            if (i < arrayConstants.length)
                allTestCases[i] = new CofinFin(false, arrayConstants[i]);
            else
                allTestCases[i] = new CofinFin(true, arrayConstants[i - arrayConstants.length]);
            System.out.println("i = " + i + " CofinFin is " + allTestCases[i]);
        }



        ////////////////  ISIN, REMOVE AND ADD TESTS


        System.out.println("\nisIn and remove and add Tests\n");

        for (int i = 0; i < arrayConstants.length * 2; i++){
            CofinFin temp = allTestCases[i];
            boolean
            isIn0 = temp.isIn(0),
            isIn1 = temp.isIn(1),
            isInMaxDiv2 = temp.isIn(MaxDiv2),
            isInMax = temp.isIn(Max);

            System.out.println("The four isIn results are " + isIn0 + " " + isIn1
                    + " " + isInMaxDiv2 + " " + isInMax);


            temp.remove(-1);   
            temp.remove(0);
            temp.remove(1);
            temp.remove(MaxDiv2);
            temp.remove(Max);
            System.out.println("i = " + i + " after the four removes, temp is " + temp);

            // restore prior value
            if (isIn0)
                temp.add(0);
            if (isIn1)
                temp.add(1);
            if (isInMaxDiv2)
                temp.add(MaxDiv2);
            if (isInMax)
                temp.add(Max);

            System.out.println("After restoration, value is " + temp);
        }



        ////////////////  MORE ADD TESTS

        // construct a few explicitly
        j = new CofinFin();
        j.isComplemented = false;
        j.theSet.add(1000);

        k = new CofinFin();
        k.isComplemented = true;
        k.theSet.add(1000);

        m = new CofinFin();
        m.isComplemented = true;
        m. theSet.add(500);

        j.add(-1);
        k.add(-2);
        m.add(Integer.MIN_VALUE);

        System.out.println("j = " + j.toString());
        System.out.println("k = " + k.toString());
        System.out.println("m = " + m.toString());





        System.out.println("\nAdd Tests\n");


        j = new CofinFin(true, new int[] { 1,2,3 });
        j.add(-1);
        j.add(Integer.MIN_VALUE);
        System.out.println("After adding -1 and " + Integer.MIN_VALUE +
                " to j, j = " + j.toString());

        for (ii = arrayConstants.length * 2; ii < allTestCases.length; ii++){
            if (allTestCases[ii] == null){  // create a new instance
                int size = rng.nextInt(10);
                allTestCases[ii] = new CofinFin(rng.nextBoolean(), null);
                for (int v = 0; v < size; v++)
                    allTestCases[ii].add(rng.nextInt(Max));
            }
            // make a copy of the current value
            CofinFin tc = new CofinFin();
            tc.isComplemented = allTestCases[ii].isComplemented;
            tc.theSet.addAll(allTestCases[ii].theSet);

            System.out.println("Testing add for " + tc.toString());
            tc.add(-2);
            System.out.println("After adding -2, new value is " + tc.toString());
            tc.add(Integer.MIN_VALUE);
            System.out.println("After adding " + Integer.MIN_VALUE + ", new value is " + tc.toString());

            for (jj = 0; jj < 100; ){
                tc.add(jj);
                System.out.println("After adding " + jj + ", new value is " + tc.toString());
                jj = 2 * jj + 1;
            }

        }



        ////////////////   UNION TESTS


        System.out.println("\nUnion Tests\n");

        for (int i = 0; i < allTestCases.length; i++){
            CofinFin outer = allTestCases[i];
            for (int j = 0; j < allTestCases.length; j++)
                System.out.println(" (i,j) = (" + i + ", " + j + ") result = " +
                        outer.union(allTestCases[j]));
        }


        // test exception
        try{
            CofinFin xxx = allTestCases[0].union(null);
            System.out.println("result is " + xxx);
        }
        catch(Exception e){
            System.out.println("Union with null other threw an exception with message '" 
                    + e.getMessage() + "'");
        }

        ////////////////////  INTERSECTION TESTS


        System.out.println("\nIntersection Tests\n");


        for (int i = 0; i < allTestCases.length; i++){
            CofinFin outer = allTestCases[i];
            for (int j = 0; j < allTestCases.length; j++)
                System.out.println(" (i,j) = (" + i + ", " + j + ") result = " +
                        outer.intersect(allTestCases[j]));
        }

        // test exception
        try{
            CofinFin xxx = allTestCases[0].intersect(null);
            System.out.println("result is " + xxx);
        }
        catch(Exception e){
            System.out.println("Intersection with null other threw an exception with message '" 
                    + e.getMessage()+ "'");
        }



        ////////////////////  COMPLEMENT TESTS

        System.out.println("\nComplement Tests\n");


        for (int i = 0; i < allTestCases.length; i++)
            System.out.println("i = " + i + " result = " +
                    allTestCases[i].complement());



        // test that complement does not share the Set data member
        j = new CofinFin(false, new int[]{1,2,3});
        m = j.complement();
        m.add(1);
        m.remove(4);
        System.out.println("after adding 1 to m and removing 4, j is " + j);
        j = new CofinFin(true, new int[]{1,2,3});
        m = j.complement();
        m.remove(1);
        m.add(4);
        System.out.println("after adding 4 to m and removing 1, j is " + j);




        ///////////////// IS IN TESTS


        System.out.println("\nisIn Tests\n");

        for (int i = 0; i < allTestCases.length; i++){
            CofinFin outer = allTestCases[i];
            for (int j = 0; j < 50; j++)
                System.out.println(" (i,j) = (" + i + ", " + j + ") isIn result = " +
                        outer.isIn(j));
        }

        // test exception
        try{
            CofinFin xxx = new CofinFin();
            xxx.add(-1);
            System.out.println("result is " + xxx);
        }
        catch(Exception e){
            System.out.println("Adding -1 threw an exception with message '" 
                    + e.getMessage()+ "'");
        }
        try{
            CofinFin xxx = new CofinFin();
            xxx.add(Integer.MIN_VALUE);
            System.out.println("result is " + xxx);
        }
        catch(Exception e){
            System.out.println("Adding integer min value threw an exception with message '" 
                    + e.getMessage()+ "'");
        }



        ///////////////// SUBSET TESTS

        System.out.println("\nSubset Tests\n");


        for (int i = 0; i < allTestCases.length; i++){
            CofinFin outer = allTestCases[i];
            for (int j = 0; j < allTestCases.length; j++)
                System.out.println(" (i,j) = (" + i + ", " + j + ") subset result = " +
                        outer.isSubsetOf(allTestCases[j]));
        }

        // test exception
        emT = new CofinFin();
        try{

            System.out.println("Result for empty set is subset of null = " + emT.isSubsetOf(null));

        }
        catch (Exception ee){
            System.out.println("Call to isSubsetOf with null other threw an exception with message '" 
                    + ee.getMessage() + "'");
        }




        ///////////////// EQUALS  TESTS

        System.out.println("\nEquals Tests\n");



        for (int i = 0; i < allTestCases.length; i++){
            CofinFin outer = allTestCases[i];
            for (int j = 0; j < allTestCases.length; j++)
                System.out.println(" (i,j) = (" + i + ", " + j + ") equals result = " +
                        outer.equals(allTestCases[j]));
        }

        // test exception
        try{

            System.out.println("empty set equals null is " + emT.equals(null));

        }
        catch (Exception e){
            System.out.println("Call to equals with null other threw an exception with message '" 
                    + e.getMessage() + "'");
        }

        ///////////////   MINIMUM TESTS

        System.out.println("\nMinimum Tests\n");

        for (ii = 0; ii < allTestCases.length; ii++){
            System.out.println("Minimum test # " + ii);
            try{
                System.out.println("Min value is " + allTestCases[ii].minimum());
            }
            catch (Exception e){
                System.out.println("The call threw an exception with message '" +
                        e.getMessage() + "'");
            }
        }


        ///////////////   MAXIMUM TESTS

        System.out.println("\nMaximum Tests\n");

        for (ii = 0; ii < allTestCases.length; ii++){
            System.out.println("Maximum test # " + ii);
            try{
                System.out.println("Max value is " + allTestCases[ii].maximum());
            }
            catch (Exception e){
                System.out.println("The call threw an exception with message '" +
                        e.getMessage() + "'");
            }
        }


        //////////////  TESTS FOR SIDE EFFECTS OF THE OPERATIONS

        System.out.println("\nSide Effect Tests.\n");

        CofinFin
        x1 = new CofinFin(),
        x2 = new CofinFin();

        x1.theSet.add(Integer.MAX_VALUE - 1);
        x2.theSet.add(Integer.MAX_VALUE - 2);
        x2.isComplemented = true;

        for (ii = 0; ii < allTestCases.length; ii++){
            allTestCases[ii].union(x2);
            x2.union(allTestCases[ii]);
            allTestCases[ii].intersect(x1);
            x1.intersect(allTestCases[ii]);
            allTestCases[ii].complement();
            System.out.println("ii = " + ii + " " + allTestCases[ii]);
        }




    }
}



