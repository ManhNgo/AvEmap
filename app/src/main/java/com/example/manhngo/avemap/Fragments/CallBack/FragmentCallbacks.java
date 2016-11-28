package com.example.manhngo.avemap.Fragments.CallBack;

/**
 * Created by Manh Ngo on 11/27/2016.
 */

public interface FragmentCallbacks {

    //Hàm truyền message từ Main Activity với giá trị truyền là một chuỗi ký tự
    public void onMsgFromMainToFragment(String strValue);
    //Hàm truyền message từ Main Activity với giá trị truyền là kiểu số nguyên chỉ vị trí
    public void onMsgFromMainToFragment_Traffic(boolean showTraffic);
    //Hàm truyền message từ Main Activity với giá trị truyền là kiểu số nguyên chỉ độ dài của mảng dữ liệu
    public void onMsgFromMainToFragmentIntLenght(int lenght);

}
