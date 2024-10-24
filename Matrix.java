/*
Code Written by Jackson L. Davis

A matrix class for creating basic matrices,
only supports matrices of integers.
 */

public class Matrix {
    private int[][] matrix;
    private int rows;
    private int columns;

    /**
     * Constructor for the Matrix class,
     * creates a zero matrix
     *
     * @param r: number of rows for the matrix
     * @param c: number of columns for the matrix
     * @precond: r > 0 && c > 0
     */
    public Matrix(int r, int c) {
        // check if r and c are positive
        if (r <= 0) {
            throw new ArithmeticException("Error in Matrix() constructor: r must be positive");
        }
        else if (c <= 0) {
            throw new ArithmeticException("Error in Matrix() constructor: c must be positive");
        }
        // create the Matrix object
        else {
            this.rows = r;
            this.columns = c;
            this.matrix = new int[r][c];
            // initialize each element in the matrix to 0
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    this.matrix[i][j] = 0;
                }
            }
        }
    }

    /**
     * Getter method for rows
     */
    public int getRows() {
        return this.rows;
    }

    /**
     * Getter method for columns
     */
    public int getColumns() {
        return this.columns;
    }

    /**
     * Getter method for matrix
     */
    public int[][] getMatrix() {
        return this.matrix;
    }

    /**
     * Get the number at a specific entry of the matrix
     * To stay consistent with how matrix entries are notated in mathematics,
     * the top row has an index of 1,
     * and the left-most column has an index of 1
     *
     * @param r: the row of the entry to get
     * @param c: the column of the entry to get
     * @precond: 1 <= r <= rows && 1 <= c <= columns
     * @return: the number at the specified entry
     */
    public int getEntry(int r, int c) {
        // subtract 1 from r and c because in programming languages,
        // indexing starts at 0 instead of 1
        r -= 1;
        c -= 1;
        // check for out of bounds indices
        if (r < 0 || c < 0 || r >= this.rows || c >= this.columns) {
            throw new ArithmeticException("Error in getEntry(): 1 <= r <= rows and 1 <= c <= columns must be true");
        }
        // return the specified entry
        else {
            return this.matrix[r][c];
        }
    }

    /**
     * Change a specific entry of the matrix
     * To stay consistent with how matrix entries are notated in mathematics,
     * the top row has an index of 1,
     * and the left-most column has an index of 1
     *
     * @param r: the row of the entry to change
     * @param c: the column of the entry to change
     * @param changeTo: the number to change the entry to
     * @precond: 1 <= r <= rows && 1 <= c <= columns
     * @postcond: the matrix entry at row r and column c is changed to changeTo if r and c are in bounds
     */
    public void setEntry(int r, int c, int changeTo) {
        // subtract 1 from r and c because in computer science,
        // indexing starts at 0 instead of 1
        r -= 1;
        c -= 1;
        // check for out of bounds indices
        if (r < 0 || c < 0 || r >= this.rows || c >= this.columns) {
            throw new ArithmeticException("Error in getEntry(): 1 <= r <= rows and 1 <= c <= columns must be true");
        }
        // change the specified entry
        else {
            this.matrix[r][c] = changeTo;
        }
    }

    /**
     * Get a specific row of a matrix
     * To stay consistent with how matrix entries are notated in mathematics,
     * the top row has an index of 1
     *
     * @param rowIndex: the row index of the matrix to get
     * @precond: 1 <= rowIndex <= rows
     * @return: a 1xn matrix
     */
    public Matrix getRowMatrix(int rowIndex) {
        // check for out of bounds index
        if (rowIndex < 1 || rowIndex > this.rows) {
            throw new ArithmeticException("Error in getRowMatrix(): 1 <= rowIndex <= rows must be true");
        }
        // return the row matrix
        else {
            Matrix rowMatrix = new Matrix(1, this.columns);
            // i starts at 1 to stay consistent with matrix entry notation
            for (int i = 1; i <= this.columns; i++) {
                rowMatrix.setEntry(1, i, getEntry(rowIndex, i));
            }
            return rowMatrix;
        }
    }

    /**
     * Get a specific column of a matrix
     * To stay consistent with how matrix entries are notated in mathematics,
     * the left-most column has an index of 1
     *
     * @param columnIndex: the column index of the matrix to get
     * @precond: 1 <= columnIndex <= columns
     * @return: an nx1 matrix
     */
    public Matrix getColumnMatrix(int columnIndex) {
        // check for out of bounds index
        if (columnIndex < 1 || columnIndex > this.columns) {
            throw new ArithmeticException("Error in getColumnMatrix(): 1 <= columnIndex <= columns must be true");
        }
        // return the column matrix
        else {
            Matrix columnMatrix = new Matrix(rows, 1);
            // i starts at 1 to stay consistent with matrix entry notation
            for (int i = 1; i <= this.rows; i++) {
                columnMatrix.setEntry(i, 1, getEntry(i, columnIndex));
            }
            return columnMatrix;
        }
    }

    /**
     * Get the transpose of the matrix (the matrix flipped on its diagonal)
     *
     * @return: the transpose of the matrix
     */
    public Matrix getTranspose() {
        Matrix transp = new Matrix(this.columns, this.rows);
        // i and j start at 1 to stay consistent with matrix entry notation
        for (int i = 1; i <= transp.getRows(); i++) {
            for (int j = 1; j <= transp.getColumns(); j++) {
                transp.setEntry(i, j, getEntry(j, i));
            }
        }
        return transp;
    }

    /**
     * Check whether or not a matrix is a square matrix
     *
     * @return true if the matrix is square, false otherwise
     */
    public boolean isSquare() {
        return this.rows == this.columns;
    }

    /**
     * Check whether or not a matrix is a zero matrix
     *
     * @return true if the matrix is a zero matrix, false otherwise
     */
    public boolean isZero() {
        // check if each entry is 0
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                if (this.matrix[i][j] != 0) {
                    return false;
                }
                else {
                    // pass
                }
            }
        }
        return true;
    }

    /**
     * Check whether or not a matrix is an identity matrix
     * (1's on the diagonal, 0's everywhere else)
     *
     * @return true if the matrix is an identity matrix, false otherwise
     */
    public boolean isIdentity() {
        if (isSquare()) {
            for (int i = 0; i < this.rows; i++) {
                for (int j = 0; j < this.columns; j++) {
                    // check for 1's on the diagonal
                    if (i == j) {
                        if (this.matrix[i][j] != 1) {
                            return false;
                        }
                        else {
                            // pass
                        }
                    }
                    // check for 0's everywhere else
                    else {
                        if (this.matrix[i][j] != 0) {
                            return false;
                        }
                        else {
                            // pass
                        }
                    }
                }
            }
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Turn the matrix into a zero matrix
     *
     * @postcond: the matrix will have zeros in all entries
     */
    public void makeZero() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                this.matrix[i][j] = 0;
            }
        }
    }

    /**
     * Turn the matrix into an identity matrix
     *
     * @precond: isSquare()
     * @postcond: if the matrix is a square matrix, the matrix will be turned into an identity matrix
     * (1's on the diagonal, 0's everywhere else)
     */
    public void makeIdentity() {
        if (isSquare()) {
            for (int i = 0; i < this.rows; i++) {
                for (int j = 0; j < this.columns; j++) {
                    if (i == j) {
                        this.matrix[i][j] = 1;
                    }
                    else {
                        this.matrix[i][j] = 0;
                    }
                }
            }
        }
        else {
            throw new ArithmeticException("Error in makeIdentity(): isSquare() must return true");
        }
    }

    /**
     * Multiply all of the entries of the matrix by a scalar
     *
     * @postcond: all of the entries of the matrix are multiplied by s
     */
    public void scalarMultiply(int s) {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                this.matrix[i][j] *= s;
            }
        }
    }

    /**
     * Compute the determinant of a matrix,
     * this method is static because it uses recursion,
     * and requires a matrix parameter in the recursive case
     *
     * @param m: the matrix to compute the determinant of
     * @precond: m.isSquare()
     * @return: the determinant of the matrix
     */
    public static int determinant(Matrix m) {
        if (m.isSquare()) {
            // base case: zero matrix: return 0
            if (m.isZero()) {
                return 0;
            }
            // base case: identity matrix: return 1
            else if (m.isIdentity()) {
                return 1;
            }
            // base case: 1x1 matrix: return the lone entry
            else if (m.getRows() == 1) {
                return m.getEntry(1, 1);
            }
            // base case: 2x2 matrix
            else if (m.getRows() == 2) {
                return m.getEntry(1, 1) * m.getEntry(2, 2) - m.getEntry(1, 2) * m.getEntry(2, 1);
            }
            // recursive case: nxn matrix for n >= 3
            else {
                int det = 0;
                boolean pastOmittedColumn = false;
                boolean addNextDet = true;
                // i, j, k start at 1 to stay consistent with matrix entry notation
                for (int i = 1; i <= m.getColumns(); i++) {
                    // set up an n-1xn-1 matrix
                    Matrix subMatrix = new Matrix(m.getRows() - 1, m.getColumns() - 1);
                    for (int j = 1; j <= subMatrix.getRows(); j++) {
                        pastOmittedColumn = false;
                        for (int k = 1; k <= subMatrix.getColumns(); k++) {
                            // subMatrix will omit the first row and the ith column of the original matrix
                            if (i == k) {
                                pastOmittedColumn = true;
                            }
                            else {
                                // pass
                            }
                            if (!pastOmittedColumn) {
                                subMatrix.setEntry(j, k, m.getEntry(j+1, k));
                            }
                            else {
                                subMatrix.setEntry(j, k, m.getEntry(j+1, k+1));
                            }
                        }
                    }
                    // starting with adding,
                    // alternate between adding and subtractring the
                    // 1,i entry of original matrix times determinant of subMatrix
                    if (addNextDet) {
                        det += m.getEntry(1, i) * determinant(subMatrix);
                        addNextDet = false;
                    }
                    else {
                        det -= m.getEntry(1, i) * determinant(subMatrix);
                        addNextDet = true;
                    }
                }
                return det;
            }
        }
        else {
            throw new ArithmeticException("Error in determinant(): m.isSquare() must return true");
        }
    }

    /**
     * Check if two matrices have the same dimensions
     * (ex. if m1 is an mxn matrix, then m2 is also an mxn matrix)
     *
     * @param m1, m2: two matrices to be compared
     * @return: true if the two matrices have the same number of rows and columns, false otherwise
     */
    public static boolean sameDimensions(Matrix m1, Matrix m2) {
        return m1.getRows() == m2.getRows() && m1.getColumns() == m2.getColumns();
    }

    /**
     * Check if two matrices are the same (i.e. their entries are the same)
     *
     * @param m1, m2: two matrices to be compared
     * @return: true if the two matrices are the same, false otherwise
     */
    public static boolean sameMatrix(Matrix m1, Matrix m2) {
        if (sameDimensions(m1, m2)) {
            // check each entry of both matrices
            // i and j start at 1 to stay consistent with matrix entry notation
            for (int i = 1; i <= m1.getRows(); i++) {
                for (int j = 1; j <= m1.getColumns(); j++) {
                    if (m1.getEntry(i, j) != m2.getEntry(i, j)) {
                        return false;
                    }
                    else {
                        // pass
                    }
                }
            }
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Check if two matrices have the opposite dimensions
     * (ex. if m1 is an mxn matrix, then m2 is an nxm matrix)
     *
     * @param m1, m2: two matrices to be compared
     * @return: true if the two matrices have the opposite dimensions, false otherwise
     */
    public static boolean oppositeDimensions(Matrix m1, Matrix m2) {
        return m1.getRows() == m2.getColumns() && m1.getColumns() == m2.getRows();
    }

    /**
     * Check if two matrices are transposes of each other
     *
     * @param m1, m2: two matrices to be compared
     * @return: true if the two matrices are tranposes of each other, false otherwise
     */
    public static boolean transposes(Matrix m1, Matrix m2) {
        if (oppositeDimensions(m1, m2)) {
            // check each entry of both matrices
            // i and j start at 1 to stay consistent with matrix entry notation
            for (int i = 1; i <= m1.getRows(); i++) {
                for (int j = 1; j <= m1.getColumns(); j++) {
                    if (m1.getEntry(i, j) != m2.getEntry(j, i)) {
                        return false;
                    }
                    else {
                        // pass
                    }
                }
            }
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Check if a dot product can be computed from the given two matrices
     * (in this program, m1 has to be a 1xn matrix,
     * and m2 has to be an nx1 matrix)
     *
     * @param m1, m2: two matrices to check
     * @return: true if a dot product can be computed from the two matrices, false otherwise
     */
    public static boolean canDot(Matrix m1, Matrix m2) {
        return oppositeDimensions(m1, m2) && m1.getRows() == 1 && m2.getColumns() == 1;
    }

    /**
     * Check if two matrices can be multiplied together
     * (in this program, if m1 is an mxn matrix and m2 is a pxq matrix,
     * then n must equal p)
     *
     * @param m1, m2: two matrices to check
     * @return: true if the two matrices can be multiplied, false otherwise
     */
    public static boolean canMultiply(Matrix m1, Matrix m2) {
        return m1.getColumns() == m2.getRows();
    }

    /**
     * Add two matrices together and return their sum
     *
     * @param m1, m2: two matrices to be added
     * @precond: sameDimensions()
     * @return: a Matrix object that is the sum of m1 and m2
     */
    public static Matrix sumOfMatrices(Matrix m1, Matrix m2) {
        if (sameDimensions(m1, m2)) {
            Matrix matrixSum = new Matrix(m1.getRows(), m1.getColumns());
            // i and j start at 1 to stay consistent with matrix entry notation
            for (int i = 1; i <= matrixSum.getRows(); i++) {
                for (int j = 1; j <= matrixSum.getColumns(); j++) {
                    matrixSum.setEntry(i, j, m1.getEntry(i, j) + m2.getEntry(i, j));
                }
            }
            return matrixSum;
        }
        else {
            throw new ArithmeticException("Error in sumOfMatrices(): sameDimensions(m1, m2) must return true");
        }
    }

    /**
     * Compute the dot product of two matrices
     *
     * @param m1, m2: two matrices to compute the dot product of
     * @precond: canDot()
     * @return: the dot product of the two matrices
     */
    public static int dotProduct(Matrix m1, Matrix m2) {
        if (canDot(m1, m2)) {
            int dp = 0;
            // i starts at 1 to stay consistent with matrix entry notation
            for (int i = 1; i <= m1.getColumns(); i++) {
                dp += m1.getEntry(1, i) * m2.getEntry(i, 1);
            }
            return dp;
        }
        else {
            throw new ArithmeticException("Error in dotProduct(): canDot(m1, m2) must return true");
        }
    }

    /**
     * Compute the product of two matrices
     *
     * @param m1, m2: two matrices to compute the product of
     * @precond: canMultiply()
     * @return: a Matrix object that is the product of the two matrices,
     * (if m1 is an mxn matrix and m2 is a nxp matrix,
     * then this method returns an mxp matrix)
     */
    public static Matrix productOfMatrices(Matrix m1, Matrix m2) {
        if (canMultiply(m1, m2)) {
            Matrix matrixProduct = new Matrix(m1.getRows(), m2.getColumns());
            // i and j start at 1 to stay consistent with matrix entry notation
            for (int i = 1; i <= matrixProduct.getRows(); i++) {
                Matrix rowMat = m1.getRowMatrix(i);
                for (int j = 1; j <= matrixProduct.getColumns(); j++) {
                    Matrix columnMat = m2.getColumnMatrix(j);
                    matrixProduct.setEntry(i, j, dotProduct(rowMat, columnMat));
                }
            }
            return matrixProduct;
        }
        else {
            throw new ArithmeticException("Error in productOfMatrices(): canMultiply(m1, m2) must return true");
        }
    }

    /**
     * Print the matrix to the console
     *
     * @postcond: the matrix is printed to the console
     */
    public void printMatrix() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                System.out.print(this.matrix[i][j] + " ");
            }
            System.out.println("");
        }
    }

    /**
     * Print a matrix to the console
     *
     * @postcond: the specified matrix is printed to the console
     */
    public static void staticPrintMatrix(Matrix m) {
        for (int i = 0; i < m.getRows(); i++) {
            for (int j = 0; j < m.getColumns(); j++) {
                System.out.print(m.getMatrix()[i][j] + " ");
            }
            System.out.println("");
        }
    }

    /**
     * Create a deep clone of the matrix
     *
     * @return: a deep clone of the matrix
     */
    public Matrix deepCloneMatrix() {
        Matrix mClone = new Matrix(this.rows, this.columns);
        // i and j start at 1 to stay consistent with matrix entry notation
        for (int i = 1; i <= this.rows; i++) {
            for (int j = 1; j <= this.columns; j++) {
                mClone.setEntry(i, j, getEntry(i, j));
            }
        }
        return mClone;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////// TEST SUITE //////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  
    public static void main(String[] args) {
        System.out.println("Test suite for Matrix.java");
        int uErrors = 0; // number of unintentional errors

        // create a couple of invalid Matrix objects
        System.out.println("Testing catching exceptions thrown by the Matrix constructor.");
        try {
            Matrix mInvalid1 = new Matrix(0, 1);
            System.out.println("Error: Matrix() constructor did not throw an exception even though the number of rows was not positive.");
            uErrors += 1;
        }
        catch (Exception e) {
            // expected result
            System.out.println("Caught error: " + e.getMessage());
        }

        try {
            Matrix mInvalid2 = new Matrix(1, 0);
            System.out.println("Error: Matrix() constructor did not throw an exception even though the number of columns was not positive.");
            uErrors += 1;
        }
        catch (Exception e) {
            // expected result
            System.out.println("Caught error: " + e.getMessage());
        }

        // create a valid Matrix object
        try {
            Matrix m1 = new Matrix(2,3);

            // print the matrix
            System.out.println("Here is an example matrix.");
            m1.printMatrix();

            // test getRows()
            if (m1.getRows() != 2) {
                System.out.println("Error: getRows() returned " + m1.getRows() + " instead of 2.");
                uErrors += 1;
            }
            else {
                // expected result
            }

            // test getColumns()
            if (m1.getColumns() != 3) {
                System.out.println("Error: getColumns() returned " + m1.getColumns() + " instead of 3.");
                uErrors += 1;
            }
            else {
                // expected result
            }

            // test getMatrix()
            System.out.println("Here is the matrix being accessed and printed using getMatrix().");
            for (int i = 0; i < m1.getRows(); i++) {
                for (int j = 0; j < m1.getColumns(); j++) {
                    System.out.print(m1.getMatrix()[i][j] + " ");
                }
                System.out.println("");
            }

            // test staticPrintMatrix()
            System.out.println("Here is the matrix being printed using staticPrintMatrix().");
            Matrix.staticPrintMatrix(m1);

            // test IsSquare()
            if (m1.isSquare()) {
                System.out.println("Error: isSquare() returned true for non-square matrix.");
                uErrors += 1;
            }
            else {
                // expected result
            }

            // test IsZero()
            if (m1.isZero()) {
                // expected result
            }
            else {
                System.out.println("Error: isZero() returned false for zero matrix.");
                uErrors += 1;
            }

            // test IsIdentity()
            if (m1.isIdentity()) {
                System.out.println("Error: isIdentity() returned true for non-identity matrix.");
                uErrors += 1;
            }
            else {
                // expected result
            }

            // test sameDimensions()
            if (!Matrix.sameDimensions(m1, m1)) {
                System.out.println("Error: sameDimensions() returned false when comparing a matrix with itself.");
                uErrors += 1;
            }
        else {
                // expected result
            }

            // test sameMatrix()
            if (!Matrix.sameMatrix(m1, m1)) {
                System.out.println("Error: sameMatrix() returned false when comparing a matrix with itself.");
                uErrors += 1;
            }
        else {
                // expected result
            }

            // test oppositeDimensions()
            if (Matrix.oppositeDimensions(m1, m1)) {
                System.out.println("Error: oppositeDimensions() returned true when comparing a 3x2 matrix with itself.");
                uErrors += 1;
            }
        else {
                // expected result
            }

            // test transposes()
            if (Matrix.transposes(m1, m1)) {
                System.out.println("Error: transposes() returned true when comparing matrices that were not transposes.");
                uErrors += 1;
            }
        else {
                // expected result
            }

            // test setEntry()
            // change some entries
            try {
                m1.setEntry(1, 1, 1);
                m1.setEntry(1, 2, 2);
                m1.setEntry(2, 3, 3);
            }
            catch (Exception e) {
                System.out.println("Error: setEntry() threw an exception even though the specified entry was in bounds.");
                System.out.println("Unintentional Error: " + e.getMessage());
                uErrors += 1;
            }
            // some invalid changes
            System.out.println("Testing catching exception thrown by setEntry().");
            try {
                m1.setEntry(0, 1, 7);
                System.out.println("Error: setEntry() did not throw an exception for an entry that was out of bounds.");
                uErrors += 1;
            }
            catch (Exception e) {
                // expected result
                System.out.println("Caught error: " + e.getMessage());
            }
            try {
                m1.setEntry(1, 0, 7);
                System.out.println("Error: setEntry() did not throw an exception for an entry that was out of bounds.");
                uErrors += 1;
            }
            catch (Exception e) {
                // expected result
            }
            try {
                m1.setEntry(3, 1, 7);
                System.out.println("Error: setEntry() did not throw an exception for an entry that was out of bounds.");
                uErrors += 1;
            }
            catch (Exception e) {
                // expected result
            }
            try {
                m1.setEntry(1, 4, 7);
                System.out.println("Error: setEntry() did not throw an exception for an entry that was out of bounds.");
                uErrors += 1;
            }
            catch (Exception e) {
                // expected result
            }

            // print the matrix
            System.out.println("After some changes...");
            m1.printMatrix();

            // test getEntry()
            System.out.println("The number at index 1,1 is " + m1.getEntry(1, 1) + ".");
            System.out.println("The number at index 2,2 is " + m1.getEntry(2, 2) + ".");
            System.out.println("The number at index 2,3 is " + m1.getEntry(2, 3) + ".");
            System.out.println("Testing catching exception thrown by getEntry().");
            try {
                m1.getEntry(0, 1);
                System.out.println("Error: getEntry() did not throw an exception for an entry that was out of bounds.");
                uErrors += 1;
            }
            catch (Exception e) {
                // expected result
                System.out.println("Caught error: " + e.getMessage());
            }
            try {
                m1.getEntry(1, 0);
                System.out.println("Error: getEntry() did not throw an exception for an entry that was out of bounds.");
                uErrors += 1;
            }
            catch (Exception e) {
                // expected result
            }
            try {
                m1.getEntry(3, 1);
                System.out.println("Error: getEntry() did not throw an exception for an entry that was out of bounds.");
                uErrors += 1;
            }
            catch (Exception e) {
                // expected result
            }
            try {
                m1.getEntry(1, 4);
                System.out.println("Error: getEntry() did not throw an exception for an entry that was out of bounds.");
                uErrors += 1;
            }
            catch (Exception e) {
                // expected result
            }

            // test IsZero()
            if (m1.isZero()) {
                System.out.println("Error: isZero() returned true for non-zero matrix.");
                uErrors += 1;
            }
            else {
                // expected result
            }

            // test scalarMultiply()
            m1.scalarMultiply(2);
            System.out.println("Scalar multiply the matrix by 2.");
            m1.printMatrix();

            // test makeZero()
            m1.makeZero();
            System.out.println("Turn matrix back into a zero matrix.");
            m1.printMatrix();

            // test IsZero()
            if (m1.isZero()) {
                // expected result
            }
            else {
                System.out.println("Error: isZero() returned false for zero matrix.");
                uErrors += 1;
            }

            // test makeIdentity()
            System.out.println("Testing catching exception thrown by makeIdentity().");
            try {
                m1.makeIdentity();
                System.out.println("Error: makeIdentity() did not throw an exception for a non-square matrix.");
                uErrors += 1;
            }
            catch (Exception e) {
                System.out.println("Caught error: " + e.getMessage());
                System.out.println("makeIdentity() will not work on a non-square matrix.");
            }

            // check if matrix is still a zero matrix
            System.out.println("Check if the matrix is still the zero matrix...");
            m1.printMatrix();

            // test IsZero()
            if (m1.isZero()) {
                // expected result
            }
            else {
                System.out.println("Error: isZero() returned false for zero matrix.");
                uErrors += 1;
            }

            // test determinant()
            System.out.println("Testing catching exception thrown by determinant().");
            try {
                int m1det = Matrix.determinant(m1);
                System.out.println("Error: determinant() did not throw an exception for a non-square matrix, but instead returned " + m1det + ".");
                uErrors += 1;
            }
            catch (Exception e) {
                System.out.println("Caught error: " + e.getMessage());
            }
        }
        catch (Exception e) {
            System.out.println("Error: Matrix() constructor threw an exception even though both parameters were positive.");
            System.out.println("Unintentional Error: " + e.getMessage());
            uErrors += 1;
        }

        // create a square matrix
        try {
            Matrix m2 = new Matrix(4, 4);

            // print the matrix
            System.out.println("Here is a square matrix.");
            m2.printMatrix();

            // test getRows()
            if (m2.getRows() != 4) {
                System.out.println("Error: getRows() returned " + m2.getRows() + " instead of 4.");
                uErrors += 1;
            }
            else {
                // expected result
            }

            // test getColumns()
            if (m2.getColumns() != 4) {
                System.out.println("Error: getColumns() returned " + m2.getColumns() + " instead of 4.");
                uErrors += 1;
            }
            else {
                // expected result
            }

            // test getMatrix()
            System.out.println("Here is the matrix being accessed and printed using getMatrix().");
            for (int i = 0; i < m2.getRows(); i++) {
                for (int j = 0; j < m2.getColumns(); j++) {
                    System.out.print(m2.getMatrix()[i][j] + " ");
                }
                System.out.println("");
            }

            // test staticPrintMatrix()
            System.out.println("Here is the matrix being printed using staticPrintMatrix().");
            Matrix.staticPrintMatrix(m2);

            // test IsSquare()
            if (m2.isSquare()) {
                // expected result
            }
            else {
                System.out.println("Error: isSquare() returned false for square matrix.");
                uErrors += 1;
            }

            // test IsZero()
            if (m2.isZero()) {
                // expected result
            }
            else {
                System.out.println("Error: isZero() returned false for zero matrix.");
                uErrors += 1;
            }

            // test IsIdentity()
            if (m2.isIdentity()) {
                System.out.println("Error: isIdentity() returned true for non-identity matrix.");
                uErrors += 1;
            }
            else {
                // expected result
            }

            // test sameDimensions()
            if (!Matrix.sameDimensions(m2, m2)) {
                System.out.println("Error: sameDimensions() returned false when comparing a matrix with itself.");
                uErrors += 1;
            }
        else {
                // expected result
            }

            // test sameMatrix()
            if (!Matrix.sameMatrix(m2, m2)) {
                System.out.println("Error: sameMatrix() returned false when comparing a matrix with itself.");
                uErrors += 1;
            }
        else {
                // expected result
            }

            // test oppositeDimensions()
            if (!Matrix.oppositeDimensions(m2, m2)) {
                System.out.println("Error: oppositeDimensions() returned false when comparing a 4x4 matrix with itself.");
                uErrors += 1;
            }
        else {
                // expected result
            }

            // test transposes()
            if (!Matrix.transposes(m2, m2)) {
                System.out.println("Error: transposes() returned false when comparing matrices that were transposes.");
                uErrors += 1;
            }
        else {
                // expected result
            }

            // test setEntry()
            // change some entries
            try {
                m2.setEntry(1, 1, 4);
                m2.setEntry(3, 3, 2);
                m2.setEntry(4, 2, 7);
                m2.setEntry(1, 4, 1);
            }
            catch (Exception e) {
                System.out.println("Error: setEntry() threw an exception even though the specified entry was in bounds.");
                System.out.println("Unintentional Error: " + e.getMessage());
                uErrors += 1;
            }
            // some invalid changes
            System.out.println("Testing catching exception thrown by setEntry().");
            try {
                m2.setEntry(0, 1, 8);
                System.out.println("Error: setEntry() did not throw an exception for an entry that was out of bounds.");
                uErrors += 1;
            }
            catch (Exception e) {
                // expected result
                System.out.println("Caught error: " + e.getMessage());
            }
            try {
                m2.setEntry(1, 0, 8);
                System.out.println("Error: setEntry() did not throw an exception for an entry that was out of bounds.");
                uErrors += 1;
            }
            catch (Exception e) {
                // expected result
            }
            try {
                m2.setEntry(5, 1, 8);
                System.out.println("Error: setEntry() did not throw an exception for an entry that was out of bounds.");
                uErrors += 1;
            }
            catch (Exception e) {
                // expected result
            }
            try {
                m2.setEntry(1, 5, 8);
                System.out.println("Error: setEntry() did not throw an exception for an entry that was out of bounds.");
                uErrors += 1;
            }
            catch (Exception e) {
                // expected result
            }

            // print the matrix
            System.out.println("After some changes...");
            m2.printMatrix();

            // test getEntry()
            System.out.println("The number at index 1,1 is " + m2.getEntry(1, 1) + ".");
            System.out.println("The number at index 4,2 is " + m2.getEntry(4, 2) + ".");
            System.out.println("The number at index 3,4 is " + m2.getEntry(3, 4) + ".");
            System.out.println("Testing catching exception thrown by getEntry().");
            try {
                m2.getEntry(0, 1);
                System.out.println("Error: getEntry() did not throw an exception for an entry that was out of bounds.");
                uErrors += 1;
            }
            catch (Exception e) {
                // expected result
                System.out.println("Caught error: " + e.getMessage());
            }
            try {
                m2.getEntry(1, 0);
                System.out.println("Error: getEntry() did not throw an exception for an entry that was out of bounds.");
                uErrors += 1;
            }
            catch (Exception e) {
                // expected result
            }
            try {
                m2.getEntry(5, 1);
                System.out.println("Error: getEntry() did not throw an exception for an entry that was out of bounds.");
                uErrors += 1;
            }
            catch (Exception e) {
                // expected result
            }
            try {
                m2.getEntry(1, 5);
                System.out.println("Error: getEntry() did not throw an exception for an entry that was out of bounds.");
                uErrors += 1;
            }
            catch (Exception e) {
                // expected result
            }

            // test IsZero()
            if (m2.isZero()) {
                System.out.println("Error: isZero() returned true for non-zero matrix.");
                uErrors += 1;
            }
            else {
                // expected result
            }

            // test scalarMultiply()
            m2.scalarMultiply(5);
            System.out.println("Scalar multiply the matrix by 5.");
            m2.printMatrix();

            // test transposes()
            if (Matrix.transposes(m2, m2)) {
                System.out.println("Error: transposes() returned true when comparing matrices that were not transposes.");
                uErrors += 1;
            }
        else {
                // expected result
            }

            // test makeIdentity()
            System.out.println("Turn the matrix into an identity matrix.");
            try {
                m2.makeIdentity();
                m2.printMatrix();
            }
            catch (Exception e) {
                System.out.println("Error: makeIdentity() threw an exception for a square matrix.");
                System.out.println("Unintentional Error: " + e.getMessage());
                uErrors += 1;
            }

            // test isIdentity()
            if (m2.isIdentity()) {
                // expected result
            }
            else {
                System.out.println("Error: isIdentity() returned false for an identity matrix.");
                uErrors += 1;
            }

            // test IsZero()
            if (m2.isZero()) {
                System.out.println("Error: isZero() returned true for non-zero matrix.");
                uErrors += 1;
            }
            else {
                // expected result
            }

            // test determinant()
            try {
                int m2detIdentity = Matrix.determinant(m2);
                System.out.println("The determinant of this matrix is " + m2detIdentity + ".");
            }
            catch (Exception e) {
                System.out.println("Error: determinant() threw an exception for a square matrix.");
                System.out.println("Unintentional Error: " + e.getMessage());
                uErrors += 1;
            }

            // test makeZero()
            m2.makeZero();
            System.out.println("Turn matrix back into a zero matrix.");
            m2.printMatrix();

            // test isIdentity()
            if (m2.isIdentity()) {
                System.out.println("Error: isIdentity() returned true for a zero matrix.");
                uErrors += 1;
            }
            else {
                // expected result
            }

            // test IsZero()
            if (m2.isZero()) {
                // expected result
            }
            else {
                System.out.println("Error: isZero() returned false for zero matrix.");
                uErrors += 1;
            }

            // test determinant()
            try {
                int m2detZero = Matrix.determinant(m2);
                System.out.println("The determinant of this matrix is " + m2detZero + ".");
            }
            catch (Exception e) {
                System.out.println("Error: determinant() threw an exception for a square matrix.");
                System.out.println("Unintentional Error: " + e.getMessage());
                uErrors += 1;
            }
        }
        catch (Exception e) {
            System.out.println("Error: Matrix() constructor threw an exception even though both parameters were positive.");
            System.out.println("Unintentional Error: " + e.getMessage());
            uErrors += 1;
        }

        // test getRowMatrix() and getColumnMatrix
        Matrix m3 = new Matrix(3,2);
        m3.setEntry(1,1,2);
        m3.setEntry(1,2,5);
        m3.setEntry(2,1,6);
        m3.setEntry(2,2,1);
        m3.setEntry(3,1,2);
        m3.setEntry(3,2,8);
        System.out.println("Here is an example matrix.");
        m3.printMatrix();
        try {
            Matrix m3row1 = m3.getRowMatrix(1);
            Matrix m3row2 = m3.getRowMatrix(2);
            Matrix m3row3 = m3.getRowMatrix(3);
            System.out.println("The first row of the matrix is");
            m3row1.printMatrix();
            System.out.println("The second row of the matrix is");
            m3row2.printMatrix();
            System.out.println("The third row of the matrix is");
            m3row3.printMatrix();
        }
        catch(Exception e) {
            System.out.println("Error: getRowMatrix() threw an exception for a valid row.");
            System.out.println("Unintentional Error: " + e.getMessage());
            uErrors += 1;
        }
        try {
            Matrix m3col1 = m3.getColumnMatrix(1);
            Matrix m3col2 = m3.getColumnMatrix(2);
            System.out.println("The first column of the matrix is");
            m3col1.printMatrix();
            System.out.println("The second column of the matrix is");
            m3col2.printMatrix();
        }
        catch (Exception e) {
            System.out.println("Error: getColumnMatrix() threw an exception for a valid column.");
            System.out.println("Unintentional Error: " + e.getMessage());
            uErrors += 1;
        }
        System.out.println("Testing catching exception thrown by getRowMatrix().");
        try {
            Matrix m3row0 = m3.getRowMatrix(0);
            System.out.println("Error: getRowMatrix() did not throw an exception for an invalid row.");
            uErrors += 1;
        }
        catch (Exception e) {
            // expected result
            System.out.println("Caught error: " + e.getMessage());
        }
        try {
            Matrix m3row4 = m3.getRowMatrix(4);
            System.out.println("Error: getRowMatrix() did not throw an exception for an invalid row.");
            uErrors += 1;
        }
        catch (Exception e) {
            // expected result
        }
        System.out.println("Testing catching exception thrown by getColumnMatrix().");
        try {
            Matrix m3col0 = m3.getColumnMatrix(0);
            System.out.println("Error: getColumnMatrix() did not throw an exception for an invalid column.");
            uErrors += 1;
        }
        catch (Exception e) {
            // expected result
            System.out.println("Caught error: " + e.getMessage());
        }
        try {
            Matrix m3col3 = m3.getColumnMatrix(3);
            System.out.println("Error: getColumnMatrix() did not throw an exception for an invalid column.");
            uErrors += 1;
        }
        catch (Exception e) {
            // expected result
        }

        // test deepCloneMatrix()
        try {
            Matrix m3Clone = m3.deepCloneMatrix();
            System.out.println("Here is a deep clone of the matrix.");
            m3Clone.printMatrix();

            // test sameDimensions()
            if (!Matrix.sameDimensions(m3, m3Clone)) {
                System.out.println("Error: sameDimensions() returned false for a matrix and its clone.");
                uErrors += 1;
            }

            // test sameMatrix()
            if (!Matrix.sameMatrix(m3, m3Clone)) {
                System.out.println("Error: sameMatrix() returned false for a matrix and its clone.");
                uErrors += 1;
            }
        }
        catch (Exception e) {
            System.out.println("Error: deepCloneMatrix() threw an exception.");
            System.out.println("Unintentional Error: " + e.getMessage());
            uErrors += 1;
        }

        // test getTranspose()
        try {
            Matrix m3Transpose = m3.getTranspose();
            System.out.println("The matrix has this transpose.");
            m3Transpose.printMatrix();

            // test sameDimensions()
            if (Matrix.sameDimensions(m3, m3Transpose)) {
                System.out.println("Error: sameDimensions() returned true for transposes with different dimensions.");
                uErrors += 1;
            }
        else {
                // expected result
            }

            // test sameMatrix()
            if (Matrix.sameMatrix(m3, m3Transpose)) {
                System.out.println("Error: sameMatrix() returned true for transposes that are different.");
                uErrors += 1;
            }
        else {
                // expected result
            }

            // test oppositeDimensions()
            if (!Matrix.oppositeDimensions(m3, m3Transpose)) {
                System.out.println("Error: oppositeDiimensions() returned false for transposes.");
                uErrors += 1;
            }
        else {
                // expected result
            }

            // test transposes()
            if (!Matrix.transposes(m3, m3Transpose)) {
                System.out.println("Error: transposes() returned false for transposes.");
                uErrors += 1;
            }
        else {
                // expected result
            }
        }
        catch (Exception e) {
            System.out.println("Error: getTranspose() threw an exception.");
            System.out.println("Unintentional Error: " + e.getMessage());
            uErrors += 1;
        }

        // test determinant()
        Matrix m1x1 = new Matrix(1, 1);
        Matrix m2x2 = new Matrix(2, 2);
        Matrix m3x3 = new Matrix(3, 3);
        Matrix m4x4 = new Matrix(4, 4);

        m1x1.setEntry(1, 1, 5);

        m2x2.setEntry(1, 1, 3);
        m2x2.setEntry(1, 2, 5);
        m2x2.setEntry(2, 1, 2);
        m2x2.setEntry(2, 2, 4);

        m3x3.setEntry(1, 1, 7);
        m3x3.setEntry(1, 2, 5);
        m3x3.setEntry(1, 3, 3);
        m3x3.setEntry(2, 1, 4);
        m3x3.setEntry(2, 2, 3);
        m3x3.setEntry(2, 3, 2);
        m3x3.setEntry(3, 1, 6);
        m3x3.setEntry(3, 2, 9);
        m3x3.setEntry(3, 3, 9);

        m4x4.setEntry(1, 1, 1);
        m4x4.setEntry(1, 2, 2);
        m4x4.setEntry(1, 3, 4);
        m4x4.setEntry(1, 4, 8);
        m4x4.setEntry(2, 1, 9);
        m4x4.setEntry(2, 2, 7);
        m4x4.setEntry(2, 3, 5);
        m4x4.setEntry(2, 4, 3);
        m4x4.setEntry(3, 1, 6);
        m4x4.setEntry(3, 2, 4);
        m4x4.setEntry(3, 3, 3);
        m4x4.setEntry(3, 4, 2);
        m4x4.setEntry(4, 1, 1);
        m4x4.setEntry(4, 2, 6);
        m4x4.setEntry(4, 3, 9);
        m4x4.setEntry(4, 4, 9);

        System.out.println("Here are four matrices and their determinants.");
        int expectedDet;
        int returnedDet;

        m1x1.printMatrix();
        expectedDet = 5;
        try {
            returnedDet = Matrix.determinant(m1x1);
            System.out.println("Determinant: " + returnedDet + ".");
            if (returnedDet != expectedDet) {
                System.out.println("Error: determinant() returned the wrong determinant.");
                System.out.println("Correct determinant: " + expectedDet + ".");
                uErrors += 1;
            }
            else {
                // expected result
            }
        }
        catch (Exception e) {
            System.out.println("Error: determinant() threw an error for a square matrix.");
            System.out.println("Unintentional Error: " + e.getMessage());
            uErrors += 1;
        }

        m2x2.printMatrix();
        expectedDet = 2;
        try {
            returnedDet = Matrix.determinant(m2x2);
            System.out.println("Determinant: " + returnedDet + ".");
            if (returnedDet != expectedDet) {
                System.out.println("Error: determinant() returned the wrong determinant.");
                System.out.println("Correct determinant: " + expectedDet + ".");
                uErrors += 1;
            }
            else {
                // expected result
            }
        }
        catch (Exception e) {
            System.out.println("Error: determinant() threw an error for a square matrix.");
            System.out.println("Unintentional Error: " + e.getMessage());
            uErrors += 1;
        }

        m3x3.printMatrix();
        expectedDet = -3;
        try {
            returnedDet = Matrix.determinant(m3x3);
            System.out.println("Determinant: " + returnedDet + ".");
            if (returnedDet != expectedDet) {
                System.out.println("Error: determinant() returned the wrong determinant.");
                System.out.println("Correct determinant: " + expectedDet + ".");
                uErrors += 1;
            }
            else {
                // expected result
            }
        }
        catch (Exception e) {
            System.out.println("Error: determinant() threw an error for a square matrix.");
            System.out.println("Unintentional Error: " + e.getMessage());
            uErrors += 1;
        }

        m4x4.printMatrix();
        expectedDet = 121;
        try {
            returnedDet = Matrix.determinant(m4x4);
            System.out.println("Determinant: " + returnedDet + ".");
            if (returnedDet != expectedDet) {
                System.out.println("Error: determinant() returned the wrong determinant.");
                System.out.println("Correct determinant: " + expectedDet + ".");
                uErrors += 1;
            }
            else {
                // expected result
            }
        }
        catch (Exception e) {
            System.out.println("Error: determinant() threw an error for a square matrix.");
            System.out.println("Unintentional Error: " + e.getMessage());
            uErrors += 1;
        }

        // test canDot()
        if (Matrix.canDot(m4x4, m4x4)) {
            System.out.println("Error: canDot() returned true for two matrices that are not 1xn and nx1.");
            uErrors += 1;
        }
    else {
            // expected result
        }

        // test sumOfMatrices()
        Matrix m4 = new Matrix(2, 3);
        Matrix m5 = new Matrix(2, 3);
        Matrix mSumExpected = new Matrix(2, 3);

        m4.setEntry(1, 1, 1);
        m4.setEntry(1, 2, 2);
        m4.setEntry(1, 3, 3);
        m4.setEntry(2, 1, 4);
        m4.setEntry(2, 2, 5);
        m4.setEntry(2, 3, 6);

        m5.setEntry(1, 1, 7);
        m5.setEntry(1, 2, 8);
        m5.setEntry(1, 3, 9);
        m5.setEntry(2, 1, 1);
        m5.setEntry(2, 2, 0);
        m5.setEntry(2, 3, 1);

        mSumExpected.setEntry(1, 1, 8);
        mSumExpected.setEntry(1, 2, 10);
        mSumExpected.setEntry(1, 3, 12);
        mSumExpected.setEntry(2, 1, 5);
        mSumExpected.setEntry(2, 2, 5);
        mSumExpected.setEntry(2, 3, 7);

        System.out.println("Here are two matrices.");
        System.out.println("First matrix.");
        m4.printMatrix();
        System.out.println("Second matrix.");
        m5.printMatrix();
        System.out.println("Here is their sum.");
        try {
            Matrix mSum = Matrix.sumOfMatrices(m4, m5);
            mSum.printMatrix();
            if (!Matrix.sameMatrix(mSum, mSumExpected)) {
                System.out.println("Error: sumOfMatrices() returned the wrong sum.");
                System.out.println("Correct Sum:");
                mSumExpected.printMatrix();
                uErrors += 1;
            }
        else {
                // expected result
            }
        }
        catch (Exception e) {
            System.out.println("Error: sumOfMatrices() threw an exception for matrices with the same dimensions.");
            System.out.println("Unintentional Error: " + e.getMessage());
            uErrors += 1;
        }

        // test canDot()
        if (Matrix.canDot(m4, m5)) {
            System.out.println("Error: canDot() returned true for two matrices that are not 1xn and nx1.");
            uErrors += 1;
        }
    else {
            // expected result
        }

        // test canMultiply()
        if (Matrix.canMultiply(m4, m5)) {
            System.out.println("Error: canMultiply() returned true for two matrices that cannot be multiplied.");
            uErrors += 1;
        }
    else {
            // expected result
        }

        // test dotProduct()
        System.out.println("Testing catching exception thrown by dotProduct().");
        try {
            int badDotProduct = Matrix.dotProduct(m4, m5);
            System.out.println("Error: dotProduct() did not throw an exception for matrices that cannot have their dot product calculated, but returned " + badDotProduct + ".");
            uErrors += 1;
        }
        catch (Exception e) {
            // expected result
            System.out.println("Caught error: " + e.getMessage());
        }

        // test productOfMatrices()
        System.out.println("Testing catching exception thrown by productOfMatrices().");
        try {
            Matrix badProduct = Matrix.productOfMatrices(m4, m5);
            System.out.println("Error: productOfMatrices() did not throw an exception for matrices that cannot be multiplied, but returned");
            badProduct.printMatrix();
            uErrors += 1;
        }
        catch (Exception e) {
            // expected result
            System.out.println("Caught error: " + e.getMessage());
        }

        // test canDot()
        Matrix mRow1 = new Matrix(1, 3);
        Matrix mCol1 = new Matrix(3, 1);
        Matrix mRow2 = new Matrix(1, 2);
        Matrix mCol2 = new Matrix(2, 1);

        mRow1.setEntry(1, 1, 6);
        mRow1.setEntry(1, 2, 4);
        mRow1.setEntry(1, 3, 2);

        mCol1.setEntry(1, 1, 2);
        mCol1.setEntry(2, 1, 5);
        mCol1.setEntry(3, 1, 0);

        mRow2.setEntry(1, 1, 7);
        mRow2.setEntry(1, 2, 3);

        mCol2.setEntry(1, 1, 8);
        mCol2.setEntry(2, 1, 1);

        if (Matrix.canDot(mRow1, mCol2)) {
            System.out.println("Error: canDot() returned true for two matrices that do not have opposite dimensions.");
            uErrors += 1;
        }
    else {
            // expected result
        }
        if (!Matrix.canDot(mRow1, mCol1)) {
            System.out.println("Error: canDot() returned false for two matrices that can have their dot product calculated.");
            uErrors += 1;
        }
    else {
            // expected result
        }
        if (!Matrix.canDot(mRow2, mCol2)) {
            System.out.println("Error: canDot() returned false for two matrices that can have their dot product calculated.");
            uErrors += 1;
        }
    else {
            // expected result
        }

        // test dotProduct()
        try {
            int badDotProduct = Matrix.dotProduct(mRow1, mCol2);
            System.out.println("Error: dotProduct() did not throw an exception for two matrices that do not have opposite dimensions, but returned " + badDotProduct + ".");
            uErrors += 1;
        }
        catch (Exception e) {
            // expected result
        }

        int expectedDotProduct;
        System.out.println("Here are two matrices.");
        System.out.println("First matrix.");
        mRow1.printMatrix();
        System.out.println("Second matrix.");
        mCol1.printMatrix();
        try {
            int dotProduct1 = Matrix.dotProduct(mRow1, mCol1);
            expectedDotProduct = 32;
            System.out.println("Their dot product is " + dotProduct1 + ".");
            if (dotProduct1 != expectedDotProduct) {
                System.out.println("Error: dotProduct() returned the wrong dot product.");
                System.out.println("Correct dot product: " + expectedDotProduct + ".");
                uErrors += 1;
            }
        }
        catch (Exception e) {
            System.out.println("Error: dotProduct() threw an exception for two matrices that can have their dot product calculated.");
            System.out.println("Unintentional Error: " + e.getMessage());
            uErrors += 1;
        }

        System.out.println("Here are two matrices.");
        System.out.println("First matrix.");
        mRow2.printMatrix();
        System.out.println("Second matrix.");
        mCol2.printMatrix();
        try {
            int dotProduct2 = Matrix.dotProduct(mRow2, mCol2);
            expectedDotProduct = 59;
            System.out.println("Their dot product is " + dotProduct2 + ".");
            if (dotProduct2 != expectedDotProduct) {
                System.out.println("Error: dotProduct() returned the wrong dot product.");
                System.out.println("Correct dot product: " + expectedDotProduct + ".");
                uErrors += 1;
            }
        }
        catch (Exception e) {
            System.out.println("Error: dotProduct() threw an exception for two matrices that can have their dot product calculated.");
            System.out.println("Unintentional Error: " + e.getMessage());
            uErrors += 1;
        }

        // test sumOfMatrices()
        System.out.println("Testing catching exception thrown by sumOfMatrices().");
        try {
            Matrix badSum = Matrix.sumOfMatrices(mRow1, mCol1);
            System.out.println("Error: sumOfMatrices() did not throw an exception for matrices of different dimensions, but returned");
            badSum.printMatrix();
            uErrors += 1;
        }
        catch (Exception e) {
            // expected result
            System.out.println("Caught error: " + e.getMessage());
        }

        // test canMultiply()
        if (!Matrix.canMultiply(mRow1, mCol1)) {
            System.out.println("Error: canMultiply() returned false for two matrices that can be multiplied.");
            uErrors += 1;
        }
    else {
            // expected result
        }

        // test canMultiply()
        Matrix m6 = new Matrix(2, 3);
        Matrix m7 = new Matrix(3, 2);
        Matrix mProductExpected1 = new Matrix(2, 2);
        Matrix mProductExpected2 = new Matrix(3, 3);

        m6.setEntry(1, 1, 2);
        m6.setEntry(1, 2, 1);
        m6.setEntry(1, 3, 7);
        m6.setEntry(2, 1, 6);
        m6.setEntry(2, 2, 4);
        m6.setEntry(2, 3, 5);

        m7.setEntry(1, 1, 3);
        m7.setEntry(1, 2, 2);
        m7.setEntry(2, 1, 1);
        m7.setEntry(2, 2, 5);
        m7.setEntry(3, 1, 8);
        m7.setEntry(3, 2, 1);

        mProductExpected1.setEntry(1, 1, 63);
        mProductExpected1.setEntry(1, 2, 16);
        mProductExpected1.setEntry(2, 1, 62);
        mProductExpected1.setEntry(2, 2, 37);

        mProductExpected2.setEntry(1, 1, 18);
        mProductExpected2.setEntry(1, 2, 11);
        mProductExpected2.setEntry(1, 3, 31);
        mProductExpected2.setEntry(2, 1, 32);
        mProductExpected2.setEntry(2, 2, 21);
        mProductExpected2.setEntry(2, 3, 32);
        mProductExpected2.setEntry(3, 1, 22);
        mProductExpected2.setEntry(3, 2, 12);
        mProductExpected2.setEntry(3, 3, 61);

        if (!Matrix.canMultiply(m6, m7)) {
            System.out.println("Error: canMultiply() returned false for two matrices that can be multiplied.");
            uErrors += 1;
        }
    else {
            // expected result
        }
        if (!Matrix.canMultiply(m7, m6)) {
            System.out.println("Error: canMultiply() returned false for two matrices that can be multiplied.");
            uErrors += 1;
        }
    else {
            // expected result
        }

        // test productOfMatrices()
        System.out.println("Here are two matrices.");
        System.out.println("First matrix.");
        m6.printMatrix();
        System.out.println("Second matrix.");
        m7.printMatrix();
        try {
            Matrix mProduct1 = Matrix.productOfMatrices(m6, m7);
            System.out.println("The first matrix multiplied by the second matrix is");
            mProduct1.printMatrix();
            if (!Matrix.sameMatrix(mProduct1, mProductExpected1)) {
                System.out.println("Error: productOfMatrices() returned the wrong product.");
                System.out.println("Correct product: ");
                mProductExpected1.printMatrix();
                uErrors += 1;
            }
        else {
                // expected result
            }
        }
        catch (Exception e) {
            System.out.println("Error: productOfMatrices() threw an exception for two matrices that can be multiplied.");
            System.out.println("Unintentional Error: " + e.getMessage());
            uErrors += 1;
        }
        try {
            Matrix mProduct2 = Matrix.productOfMatrices(m7, m6);
            System.out.println("The second matrix multiplied by the first matrix is");
            mProduct2.printMatrix();
            if (!Matrix.sameMatrix(mProduct2, mProductExpected2)) {
                System.out.println("Error: productOfMatrices() returned the wrong product.");
                System.out.println("Correct product: ");
                mProductExpected2.printMatrix();
                uErrors += 1;
            }
        else {
                // expected result
            }
        }
        catch (Exception e) {
            System.out.println("Error: productOfMatrices() threw an exception for two matrices that can be multiplied.");
            System.out.println("Unintentional Error: " + e.getMessage());
            uErrors += 1;
        }

        System.out.println("Testing complete with " + uErrors + " unintentional errors.");
    }
}
