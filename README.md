# GitHub
https://github.com/YiDang/FNCB.git
# FNCB
The followings are included:  
1.A eclipse java project.  
2.Runnable jar.  
3.Input files.  

To run the program, just copy the following text into command line:  
java -jar .\run.jar [input file path]

If [input file path] is left empty, the program will run the with default input given in the project description.

# OUTPUT
## Given input
### Input
A B C D E  
A B -> C  
C -> D  
D -> B E  
### Result
Part 1  
{D->BE,DE->B,C->BDE,CE->BD,CD->BE,CDE->B,BD->E,BC->DE,BCE->D,BCD->E,AD->BCE,ADE->BC,AC->BDE,ACE->BD,ACD->BE,ACDE->B,AB->CDE,ABE->CD,ABD->CE,ABDE->C,ABC->DE,ABCE->D,ABCD->E}

Part 2  
{D->BE,DE->B,C->BDE,CE->BD,CD->BE,CDE->B,BD->E,BC->DE,BCE->D,BCD->E}

Part 3  

Decomposed Relations:
{CD,BDE,AC}

Corresponding FDs:
{C->D}
{D->BE,DE->B,BD->E}
{}
## Another input
### Input
A B C D E  
A B -> C  
C -> D  
D -> E  
### Result
Part 1  
{D->E,C->DE,CE->D,CD->E,BD->E,BC->DE,BCE->D,BCD->E,AD->E,AC->DE,ACE->D,ACD->E,AB->CDE,ABE->CD,ABD->CE,ABDE->C,ABC->DE,ABCE->D,ABCD->E}

Part 2  
{D->E,C->DE,CE->D,CD->E,BD->E,BC->DE,BCE->D,BCD->E,AD->E,AC->DE,ACE->D,ACD->E}

Part 3  

Decomposed Relations:
{DE,CD,ABC}

Corresponding FDs:
{D->E}
{C->D}
{AB->C}
## A more complicated example
Its corresponding input file is input2, the content of which is as follows:  
A B C D E F G H I J K L M  
A -> B C D E  
E -> F G H  
I -> J    
A I -> K  
A L -> M  
You can compare the result with the answer on   
http://www.mathcs.emory.edu/~cheung/Courses/377/Syllabus/9-NormalForms/examples.html
