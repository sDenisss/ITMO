﻿#pragma checksum "..\..\..\..\View\Gauss_Seidel_MethodWindow.xaml" "{ff1816ec-aa5e-4d10-87f7-6f4963833460}" "9B5B9DBA9DE7B53BB4587CCBC80B36185CEE135E"
//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated by a tool.
//     Runtime Version:4.0.30319.42000
//
//     Changes to this file may cause incorrect behavior and will be lost if
//     the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

using CM1Lab.View;
using OxyPlot.Wpf;
using System;
using System.Diagnostics;
using System.Windows;
using System.Windows.Automation;
using System.Windows.Controls;
using System.Windows.Controls.Primitives;
using System.Windows.Controls.Ribbon;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Ink;
using System.Windows.Input;
using System.Windows.Markup;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Media.Effects;
using System.Windows.Media.Imaging;
using System.Windows.Media.Media3D;
using System.Windows.Media.TextFormatting;
using System.Windows.Navigation;
using System.Windows.Shapes;
using System.Windows.Shell;


namespace CM1Lab.View {
    
    
    /// <summary>
    /// Gauss_Seidel_MethodWindow
    /// </summary>
    public partial class Gauss_Seidel_MethodWindow : System.Windows.Window, System.Windows.Markup.IComponentConnector {
        
        
        #line 31 "..\..\..\..\View\Gauss_Seidel_MethodWindow.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal System.Windows.Controls.TextBox Accuracy;
        
        #line default
        #line hidden
        
        
        #line 34 "..\..\..\..\View\Gauss_Seidel_MethodWindow.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal System.Windows.Controls.TextBox Size;
        
        #line default
        #line hidden
        
        
        #line 68 "..\..\..\..\View\Gauss_Seidel_MethodWindow.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal System.Windows.Controls.Grid CoefficientGrid;
        
        #line default
        #line hidden
        
        private bool _contentLoaded;
        
        /// <summary>
        /// InitializeComponent
        /// </summary>
        [System.Diagnostics.DebuggerNonUserCodeAttribute()]
        [System.CodeDom.Compiler.GeneratedCodeAttribute("PresentationBuildTasks", "9.0.1.0")]
        public void InitializeComponent() {
            if (_contentLoaded) {
                return;
            }
            _contentLoaded = true;
            System.Uri resourceLocater = new System.Uri("/CM1Lab;V1.0.0.0;component/view/gauss_seidel_methodwindow.xaml", System.UriKind.Relative);
            
            #line 1 "..\..\..\..\View\Gauss_Seidel_MethodWindow.xaml"
            System.Windows.Application.LoadComponent(this, resourceLocater);
            
            #line default
            #line hidden
        }
        
        [System.Diagnostics.DebuggerNonUserCodeAttribute()]
        [System.CodeDom.Compiler.GeneratedCodeAttribute("PresentationBuildTasks", "9.0.1.0")]
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Never)]
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Design", "CA1033:InterfaceMethodsShouldBeCallableByChildTypes")]
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Maintainability", "CA1502:AvoidExcessiveComplexity")]
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1800:DoNotCastUnnecessarily")]
        void System.Windows.Markup.IComponentConnector.Connect(int connectionId, object target) {
            switch (connectionId)
            {
            case 1:
            this.Accuracy = ((System.Windows.Controls.TextBox)(target));
            return;
            case 2:
            this.Size = ((System.Windows.Controls.TextBox)(target));
            return;
            case 3:
            
            #line 39 "..\..\..\..\View\Gauss_Seidel_MethodWindow.xaml"
            ((System.Windows.Controls.Button)(target)).Click += new System.Windows.RoutedEventHandler(this.ConfirmClick);
            
            #line default
            #line hidden
            return;
            case 4:
            this.CoefficientGrid = ((System.Windows.Controls.Grid)(target));
            return;
            case 5:
            
            #line 76 "..\..\..\..\View\Gauss_Seidel_MethodWindow.xaml"
            ((System.Windows.Controls.Button)(target)).Click += new System.Windows.RoutedEventHandler(this.ChooseWayClick);
            
            #line default
            #line hidden
            return;
            case 6:
            
            #line 77 "..\..\..\..\View\Gauss_Seidel_MethodWindow.xaml"
            ((System.Windows.Controls.Button)(target)).Click += new System.Windows.RoutedEventHandler(this.CountResults);
            
            #line default
            #line hidden
            return;
            }
            this._contentLoaded = true;
        }
    }
}

