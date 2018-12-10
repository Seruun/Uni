function varargout = guiFunktionen(varargin)
% GUIFUNKTIONEN MATLAB code for guiFunktionen.fig
%      GUIFUNKTIONEN, by itself, creates a new GUIFUNKTIONEN or raises the existing
%      singleton*.
%
%      H = GUIFUNKTIONEN returns the handle to a new GUIFUNKTIONEN or the handle to
%      the existing singleton*.
%
%      GUIFUNKTIONEN('CALLBACK',hObject,eventData,handles,...) calls the local
%      function named CALLBACK in GUIFUNKTIONEN.M with the given input arguments.
%
%      GUIFUNKTIONEN('Property','Value',...) creates a new GUIFUNKTIONEN or raises the
%      existing singleton*.  Starting from the left, property value pairs are
%      applied to the GUI before guiFunktionen_OpeningFcn gets called.  An
%      unrecognized property name or invalid value makes property application
%      stop.  All inputs are passed to guiFunktionen_OpeningFcn via varargin.
%
%      *See GUI Options on GUIDE's Tools menu.  Choose "GUI allows only one
%      instance to run (singleton)".
%
% See also: GUIDE, GUIDATA, GUIHANDLES

% Edit the above text to modify the response to help guiFunktionen

% Last Modified by GUIDE v2.5 16-Jan-2018 15:58:17

% Begin initialization code - DO NOT EDIT
gui_Singleton = 1;
gui_State = struct('gui_Name',       mfilename, ...
                   'gui_Singleton',  gui_Singleton, ...
                   'gui_OpeningFcn', @guiFunktionen_OpeningFcn, ...
                   'gui_OutputFcn',  @guiFunktionen_OutputFcn, ...
                   'gui_LayoutFcn',  [] , ...
                   'gui_Callback',   []);
if nargin && ischar(varargin{1})
    gui_State.gui_Callback = str2func(varargin{1});
end

if nargout
    [varargout{1:nargout}] = gui_mainfcn(gui_State, varargin{:});
else
    gui_mainfcn(gui_State, varargin{:});
end
% End initialization code - DO NOT EDIT


% --- Executes just before guiFunktionen is made visible.
function guiFunktionen_OpeningFcn(hObject, eventdata, handles, varargin)
% This function has no output args, see OutputFcn.
% hObject    handle to figure
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)
% varargin   command line arguments to guiFunktionen (see VARARGIN)

% Choose default command line output for guiFunktionen
handles.output = hObject;

% Update handles structure
guidata(hObject, handles);

% UIWAIT makes guiFunktionen wait for user response (see UIRESUME)
% uiwait(handles.figure1);

% OnLoad default data
fcnlist{1} = "Gerade";
fcnlist{2} = "Polynom 3. Grades";
fcnlist{3} = "Exponentialfunktion";

set(handles.FunctionListBox, 'String', fcnlist);

usglist{1} = "Reload";
usglist{2} = "Mittelwert / Statistik";
usglist{3} = "Extrema";

set(handles.UsageListBox, 'String', usglist);

% --- Outputs from this function are returned to the command line.
function varargout = guiFunktionen_OutputFcn(hObject, eventdata, handles) 
% varargout  cell array for returning output args (see VARARGOUT);
% hObject    handle to figure
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Get default command line output from handles structure
varargout{1} = handles.output;



function AEditTxt_Callback(hObject, eventdata, handles)
% hObject    handle to AEditTxt (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Hints: get(hObject,'String') returns contents of AEditTxt as text
%        str2double(get(hObject,'String')) returns contents of AEditTxt as a double


% --- Executes during object creation, after setting all properties.
function AEditTxt_CreateFcn(hObject, eventdata, handles)
% hObject    handle to AEditTxt (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    empty - handles not created until after all CreateFcns called

% Hint: edit controls usually have a white background on Windows.
%       See ISPC and COMPUTER.
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end
set(hObject, 'String', '0');


function BEditTxt_Callback(hObject, eventdata, handles)
% hObject    handle to BEditTxt (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Hints: get(hObject,'String') returns contents of BEditTxt as text
%        str2double(get(hObject,'String')) returns contents of BEditTxt as a double


% --- Executes during object creation, after setting all properties.
function BEditTxt_CreateFcn(hObject, eventdata, handles)
% hObject    handle to BEditTxt (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    empty - handles not created until after all CreateFcns called

% Hint: edit controls usually have a white background on Windows.
%       See ISPC and COMPUTER.
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end
set(hObject, 'String', '1');


function DEditTxt_Callback(hObject, eventdata, handles)
% hObject    handle to DEditTxt (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Hints: get(hObject,'String') returns contents of DEditTxt as text
%        str2double(get(hObject,'String')) returns contents of DEditTxt as a double


% --- Executes during object creation, after setting all properties.
function DEditTxt_CreateFcn(hObject, eventdata, handles)
% hObject    handle to DEditTxt (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    empty - handles not created until after all CreateFcns called

% Hint: edit controls usually have a white background on Windows.
%       See ISPC and COMPUTER.
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end
set(hObject, 'String', '0');


function CEditTxt_Callback(hObject, eventdata, handles)
% hObject    handle to CEditTxt (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Hints: get(hObject,'String') returns contents of CEditTxt as text
%        str2double(get(hObject,'String')) returns contents of CEditTxt as a double


% --- Executes during object creation, after setting all properties.
function CEditTxt_CreateFcn(hObject, eventdata, handles)
% hObject    handle to CEditTxt (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    empty - handles not created until after all CreateFcns called

% Hint: edit controls usually have a white background on Windows.
%       See ISPC and COMPUTER.
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end
set(hObject, 'String', '1');

% --- Executes on selection change in UsageListBox.
function UsageListBox_Callback(hObject, eventdata, handles)
% hObject    handle to UsageListBox (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Hints: contents = cellstr(get(hObject,'String')) returns UsageListBox contents as cell array
%        contents{get(hObject,'Value')} returns selected item from UsageListBox
selectedU   = get(hObject, 'Value');
funcFunction = get(handles.FunctionListBox, 'Value');


a = str2double(get(handles.AEditTxt, 'String'));
b = str2double(get(handles.BEditTxt, 'String'));
c = str2double(get(handles.CEditTxt, 'String'));
d = str2double(get(handles.DEditTxt, 'String'));

if funcFunction == 1
    x = [-5:0.001:5];
    y = x*a+b;
elseif funcFunction == 2
    x = [-5:0.001:5];
    y = (a*(x.^3) + b*(x.^2) + c*x + d);
elseif funcFunction == 3
    x = [-5:0.001:5];
    y = (a*exp(b*x));
end

mw = num2str(mean(y));
st = num2str(std(y));
mittel = strcat("Mittelwert = ", mw);
stand  = strcat("Standardabweichung = ", st);
extrem_Min = num2str(min(y));
extrem_Max = num2str(max(y));
extremwert = strcat("Minimum = ", extrem_Min, " Maximum = ", extrem_Max);

usgfunc(1).name = "Reload";
usgfunc(1).cont(1) = "";
usgfunc(1).cont(2) = "";
usgfunc(2).name = "Mittelwert / Statistik";
usgfunc(2).cont(1) = mittel;
usgfunc(2).cont(2) = stand;
usgfunc(3).name = "Extrema";
usgfunc(3).cont(1) = "Extremwerte";
usgfunc(3).cont(2) = extremwert;

if selectedU == 1
    set(handles.Res1Txt , 'String', usgfunc(1).cont(1));
    set(handles.Res2Txt , 'String', usgfunc(1).cont(2));
    drawnow update;
elseif selectedU == 2
    set(handles.Res1Txt , 'String', usgfunc(2).cont(1));
    set(handles.Res2Txt , 'String', usgfunc(2).cont(2));
    
elseif selectedU == 3
    set(handles.Res1Txt , 'String', usgfunc(3).cont(1));
    set(handles.Res2Txt , 'String', usgfunc(3).cont(2));
end

% --- Executes during object creation, after setting all properties.
function UsageListBox_CreateFcn(hObject, eventdata, handles)
% hObject    handle to UsageListBox (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    empty - handles not created until after all CreateFcns called

% Hint: listbox controls usually have a white background on Windows.
%       See ISPC and COMPUTER.
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end


% --- Executes on selection change in FunctionListBox.
function FunctionListBox_Callback(hObject, eventdata, handles)
% hObject    handle to FunctionListBox (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Hints: contents = cellstr(get(hObject,'String')) returns FunctionListBox contents as cell array
%        contents{get(hObject,'Value')} returns selected item from FunctionListBox
selected    = get(hObject, 'Value');
content     = get(hObject, 'String');
valueSelect = content{selected};

func(1).name = "Gerade";
func(1).cont = "y = ax + b";
func(2).name = "Polynom 3. Grades";
func(2).cont = "y = ax^3 + bx^2 + cx +d";
func(3).name = "Exponentialfunktion";
func(3).cont = "y = a*e^bx";

a = str2double(get(handles.AEditTxt, 'String'));
b = str2double(get(handles.BEditTxt, 'String'));
c = str2double(get(handles.CEditTxt, 'String'));
d = str2double(get(handles.DEditTxt, 'String'));

if valueSelect == func(1).name
    set(handles.FunctionDescriptionTxt , 'String', func(1).cont);
    x = [-5:0.001:5];
    y = x*a+b;
    plot(x,y), xlabel('x'), xlabel('y');
    title(func(1).name);
    
elseif valueSelect == func(2).name
    set(handles.FunctionDescriptionTxt , 'String', func(2).cont);
    x = [-5:0.001:5];
    y = (a*(x.^3) + b*(x.^2) + c*x + d);
    plot(x, y), xlabel('x'), xlabel('y');    
    title(func(2).name);
elseif valueSelect == func(3).name
    set(handles.FunctionDescriptionTxt , 'String', func(3).cont);
    x = [-5:0.001:5];
    y = (a*exp(b*x));
    plot(x, y), xlabel('x'), xlabel('y'); 
    title(func(3).name);
end


% --- Executes during object creation, after setting all properties.
function FunctionListBox_CreateFcn(hObject, eventdata, handles)
% hObject    handle to FunctionListBox (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    empty - handles not created until after all CreateFcns called

% Hint: listbox controls usually have a white background on Windows.
%       See ISPC and COMPUTER.
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end
