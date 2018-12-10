function varargout = AGui(varargin)
% AGUI MATLAB code for AGui.fig
%      AGUI, by itself, creates a new AGUI or raises the existing
%      singleton*.
%
%      H = AGUI returns the handle to a new AGUI or the handle to
%      the existing singleton*.
%
%      AGUI('CALLBACK',hObject,eventData,handles,...) calls the local
%      function named CALLBACK in AGUI.M with the given input arguments.
%
%      AGUI('Property','Value',...) creates a new AGUI or raises the
%      existing singleton*.  Starting from the left, property value pairs are
%      applied to the GUI before AGui_OpeningFcn gets called.  An
%      unrecognized property name or invalid value makes property application
%      stop.  All inputs are passed to AGui_OpeningFcn via varargin.
%
%      *See GUI Options on GUIDE's Tools menu.  Choose "GUI allows only one
%      instance to run (singleton)".
%
% See also: GUIDE, GUIDATA, GUIHANDLES

% Edit the above text to modify the response to help AGui

% Last Modified by GUIDE v2.5 09-Jan-2018 14:50:58

% Begin initialization code - DO NOT EDIT
gui_Singleton = 1;
gui_State = struct('gui_Name',       mfilename, ...
                   'gui_Singleton',  gui_Singleton, ...
                   'gui_OpeningFcn', @AGui_OpeningFcn, ...
                   'gui_OutputFcn',  @AGui_OutputFcn, ...
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

% --- Executes just before AGui is made visible.

%% Tweaks



function setFile_chosen(x)
global file_chosen 
disp('Set:')
disp(x)
file_chosen = x;

function r = getFile_chosen()
global file_chosen
disp('Get:')
disp(file_chosen)
r = file_chosen;

function Load()
file = 'data/'


function Median()


function Maximal()


function AGui_OpeningFcn(hObject, eventdata, handles, varargin)
% This function has no output args, see OutputFcn.
% hObject    handle to figure
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)
% varargin   command line arguments to AGui (see VARARGIN)

% Choose default command line output for AGui
handles.output = hObject;

% Update handles structure
guidata(hObject, handles);

% UIWAIT makes AGui wait for user response (see UIRESUME)
% uiwait(handles.figure1);

currdir = pwd();
set(handles.file_path,'String',currdir);





% --- Outputs from this function are returned to the command line.
function varargout = AGui_OutputFcn(hObject, eventdata, handles) 
% varargout  cell array for returning output args (see VARARGOUT);
% hObject    handle to figure
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Get default command line output from handles structure
varargout{1} = handles.output;


% --- Executes on selection change in listbox_main.
function listbox_main_Callback(hObject, eventdata, handles)
% hObject    handle to listbox_main (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Hints: contents = cellstr(get(hObject,'String')) returns listbox_main contents as cell array
%        contents{get(hObject,'Value')} returns selected item from listbox_main
%global listB



val = get(hObject, 'Value');
list = get(hObject, 'String');
disp('Listbox:')
disp(list(val,:))
setFile_chosen(list(val,:))


%disp(listB(:,val))


% --- Executes during object creation, after setting all properties.
function listbox_main_CreateFcn(hObject, eventdata, handles)
% hObject    handle to listbox_main (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    empty - handles not created until after all CreateFcns called

% Hint: listbox controls usually have a white background on Windows.
%       See ISPC and COMPUTER.
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end






function file_path_Callback(hObject, eventdata, handles)
% hObject    handle to file_path (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Hints: get(hObject,'String') returns contents of file_path as text
%        str2double(get(hObject,'String')) returns contents of file_path as a double


% --- Executes during object creation, after setting all properties.
function file_path_CreateFcn(hObject, eventdata, handles)
% hObject    handle to file_path (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    empty - handles not created until after all CreateFcns called

% Hint: edit controls usually have a white background on Windows.
%       See ISPC and COMPUTER.
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end


% --- Executes on button press in button_folder.
function button_folder_Callback(hObject, eventdata, handles)
% hObject    handle to button_folder (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

directory = uigetdir(pwd());

if directory == 0
    
elseif directory ~= 0
    file_list = dir(directory);
    for i = 3:size(file_list,1)
        listB(i-2,:) = file_list(i,:);
        %disp(list(i,:)
        set(handles.listbox_main, 'String', listB(i-2,:));
    end
    
    set(handles.file_path,'String',directory);
end






% --- Executes on button press in button_display.
function button_display_Callback(hObject, eventdata, handles)
% hObject    handle to button_display (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

%global file_chosen
file_chosen = getFile_chosen();
disp('Display:')
disp(file_chosen)

if file_chosen ~= 0
    importdata('data\TestEKG.dat')
   
    
end

% --- Executes on button press in button_analyze.
function button_analyze_Callback(hObject, eventdata, handles)
% hObject    handle to button_analyze (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)
