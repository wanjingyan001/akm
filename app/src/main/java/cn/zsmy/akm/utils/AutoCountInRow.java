package cn.zsmy.akm.utils;

/**
 * Created by sanz on 2015/12/19 15:43
 * 根据手机屏幕宽度，计算gridview每个单元格的宽度
 * @param @screenWidth 屏幕宽度
 * @param @width 单元格预设宽度
 * @param @padding 单元格间距
 */
public class AutoCountInRow {
    //单元格宽度
    public int width = 0;
    //每行所能容纳的单元格数量
    public int countInRow = 0;
    public  static AutoCountInRow  calculateColumnWidthAndCountInRow(int screenWidth,int width,int padding){
        AutoCountInRow colInfo = new AutoCountInRow();
        int colCount = 0;
        //判断屏幕是否刚好能容纳下整数个单元格，若不能，则将多出的宽度保存到space中
        int space = screenWidth % width;
        if( space == 0 ){ //正好容纳下
            colCount = screenWidth / width;
        }else if( space >= ( width / 2 ) ){ //多出的宽度大于单元格宽度的一半时，则去除最后一个单元格，将其所占的宽度平分并增加到其他每个单元格中
            colCount = screenWidth / width;
            space = width - space;
            width = width + space / colCount;
        }else{  //多出的宽度小于单元格宽度的一半时，则将多出的宽度平分，并让每个单元格减去平分后的宽度
            colCount = screenWidth / width + 1;
            width = width - space / colCount;
        }
        colInfo.countInRow = colCount;
        //计算出每行的间距总宽度，并根据单元格的数量重新调整单元格的宽度
        colInfo.width = width - (( colCount + 1 ) * padding ) / colCount;
        return colInfo;
    }
    public AutoCountInRow getColumnInfo(){
        return new AutoCountInRow();
    }
}
