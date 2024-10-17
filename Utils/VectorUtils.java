package NumMetods.Utils;

import NumMetods.SystemComponents.VectorSolutionsSystem;

public class VectorUtils {
    public static VectorSolutionsSystem add(VectorSolutionsSystem v1, VectorSolutionsSystem v2) {
        int size = v1.length();
        VectorSolutionsSystem vSum = new VectorSolutionsSystem(v1);
        for (int i = 0; i < size; i++) {
            vSum.setEl(i, vSum.getEl(i).add(v2.getEl(i)));
        }
        return vSum;
    }
}
