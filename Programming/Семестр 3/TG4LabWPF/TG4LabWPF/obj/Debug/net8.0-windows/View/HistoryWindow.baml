   M S B A M L   `   `   `  ����D  @TG4LabWPF, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null/clr-namespace:TG4LabWPF.ViewTG4LabWPF.View  5   	   "    TG4LabWPF.View.HistoryWindow   5   	   W SPresentationCore, Version=8.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35\ XPresentationFramework, Version=8.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35e aSystem.Windows.Controls.Ribbon, Version=8.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089R NWindowsBase, Version=8.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35F 9http://schemas.microsoft.com/winfx/2006/xaml/presentation     5   	   R NSystem.Xaml, Version=8.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     5   	   @d2http://schemas.microsoft.com/expression/blend/2008     5   	   Jmc;http://schemas.openxmlformats.org/markup-compatibility/2006     5   	   .localclr-namespace:TG4LabWPF.View     5   	   -   5          �Name$  History_Window��5   4    � Title$ HistoryWindow��5   	   $
��1080��6   $
��1920��6-   .��5	      �  � RowDefinitions 5
   
   �� 5      $��2* �6   �� 5      $��8* �6    � ColumnDefinitions 5   
   �� 5      $��1* �6   .+�5   
   �� ���#2d2d44$��0��6#   $��0��60   $��2��6@   �� 5   
   -   5        $��50��5      $��50��6   $��30(�6(   
�������$��20q�6I   $
��Left=�6U   ���#222244$
��Bold"�5      .��6M   <6S   �� 5   
   $��0��6   $��0��6"   $��2��62   $��20q�6F   .+�5      �� $��30(�6   
�������$��Center=�69   $
��Bold"�5      $'��!Туристический Гид��6*   5      �� 5   
   $��0q�6   $��2��6    $��0��6-   .+�5       �� $��Center=�6   $
��Bold"�65   $��30(�6G   $'��!История маршрутов��6U   �� 5!      $��Center=�6   $
��Bold"�65   $��18(�6G   $��Результаты:��6U   $��0,10,0,2q�6h   �� 5"      $
��Auto=�6   $��Disabled=�6>   $	��300��6g   .��5#      �� -   5         ��Name$ history��5#      $	��600��6,   $��Center=�68   $��0,10,0,0q�6U    �� View 5$      �� 5%      .P�5&      �� $��Название��6-    �� Width$	 600��6`    �� DisplayMemberBinding 6?   ��  �� CellTemplate 5'   "   �� 5(   &   5*   '   5,      5-      5/      50      51      �� 52   
   $��3��6   $��0��6"   	 �� Orientation$	 
Horizontal=�62   $��Center=�6K   $��0,40,0,0q�6h   .+�53      �� -   5        $��35��53      $	��100��6!   $��Очистить��6-   $��14(�6@   $��5q�6N   ���#27AE60
�������54   5   55      57      58      