function validateForm()                                    
{ 
    var name = document.forms["admin"]["username"];               
    var email = document.forms["admin"]["email"];    
    var what =  document.forms["admin"]["company"];  
    var password = document.forms["admin"]["psw"];  
    var confirm = document.forms["admin"]["cpsw"];  
   
    if (name.value == "")                                  
    { 
        window.alert("Please enter your name."); 
        name.focus(); 
        return false; 
    } 
   
    if (confirm.value == "")                               
    { 
        window.alert("Please reenter your password."); 
        confirm.focus(); 
        return false; 
    } 
       
    if (email.value == "")                                   
    { 
        window.alert("Please enter a valid e-mail address."); 
        email.focus(); 
        return false; 
    } 
   
    if (email.value.indexOf("@", 0) < 0)                 
    { 
        window.alert("Please enter a valid e-mail address."); 
        email.focus(); 
        return false; 
    } 
   
    if (email.value.indexOf(".", 0) < 0)                 
    { 
        window.alert("Please enter a valid e-mail address."); 
        email.focus(); 
        return false; 
    } 
   
   
    if (password.value == "")                        
    { 
        window.alert("Please enter your password"); 
        password.focus(); 
        return false; 
    } 
   
    if (what.selectedIndex < 1)                  
    { 
        alert("Please select a Company Name."); 
        what.focus(); 
        return false; 
    } 
   
    return true; 
}


function validatePostJob()                                    
{ 
    var location = document.forms["postjob"]["location"];               
    var description = document.forms["postjob"]["description"]; 
    var what =  document.forms["postjob"]["job"];  
    var salary = document.forms["postjob"]["salary"];  
    var count = document.forms["postjob"]["count"];  
   
    if (what.selectedIndex < 1)                  
    { 
        alert("Please select a job Designation."); 
        what.focus(); 
        return false; 
    } 
   

    if (location.value == "")                                  
    { 
        window.alert("Please enter the location of job."); 
        location.focus(); 
        return false; 
    }

    if (salary.value == "")                               
    { 
        window.alert("Please enter a valid salary amount."); 
        salary.focus(); 
        return false; 
    }

    if (count.value == "")                               
    { 
        window.alert("Please enter a valid vacancy count."); 
        count.focus(); 
        return false; 
    } 

    if (description.value == "")                                  
    { 
        window.alert("Please enter job description."); 
        description.focus(); 
        return false; 
    }       
    
    return true; 
}
function validateRequestVacancy(){
    var location = document.forms["requestvacancy"]["location"];               
    var what =  document.forms["requestvacancy"]["job"];  
    var salary = document.forms["requestvacancy"]["salary"];   
   
    if (what.selectedIndex < 1)                  
    { 
        alert("Please select a job Designation."); 
        what.focus(); 
        return false; 
    } 
   

    if (location.value == "")                                  
    { 
        window.alert("Please enter the location of job."); 
        location.focus(); 
        return false; 
    }

    if (salary.value == "")                               
    { 
        window.alert("Please enter a valid salary amount."); 
        salary.focus(); 
        return false; 
    }
    
    return true; 
}

function updateUser()                                    
{ 
    var name = document.forms["update"]["username"];               
    var email = document.forms["update"]["email"];    
    var cname =  document.forms["update"]["cname"];  
    var password = document.forms["update"]["psw"];  
    var designation = document.forms["update"]["designation"];  
   
    if (name.value == "")                                  
    { 
        window.alert("Please enter your name."); 
        name.focus(); 
        return false; 
    } 
   
    if (designation.value == "")                               
    { 
        window.alert("Please enter your designation."); 
        desiognation.focus(); 
        return false; 
    } 
       
    if (email.value == "")                                   
    { 
        window.alert("Please enter a valid e-mail address."); 
        email.focus(); 
        return false; 
    } 
   
    if (email.value.indexOf("@", 0) < 0)                 
    { 
        window.alert("Please enter a valid e-mail address."); 
        email.focus(); 
        return false; 
    } 
   
    if (email.value.indexOf(".", 0) < 0)                 
    { 
        window.alert("Please enter a valid e-mail address."); 
        email.focus(); 
        return false; 
    } 
   
   
    if (password.value == "")                        
    { 
        window.alert("Please enter your password"); 
        password.focus(); 
        return flase; 
    } 
    if (cname.value == "")                        
    { 
        window.alert("Please enter your company name"); 
        cname.focus(); 
        return flase; 
    } 
   
    
   
    return true; 
}
function applyForJob()                                    
{ 
    var name = document.forms["applyforjob"]["username"];               
    var email = document.forms["applyforjob"]["email"];    
    var count = document.forms["applyforjob"]["years"];  
    var resume = document.forms["applyforjob"]["resume"];
   
    if (name.value == "")                                  
    { 
        window.alert("Please enter your name."); 
        name.focus(); 
        return false; 
    } 
       
    if (email.value == "")                                   
    { 
        window.alert("Please enter a valid e-mail address."); 
        email.focus(); 
        return false; 
    } 
   
    if (email.value.indexOf("@", 0) < 0)                 
    { 
        window.alert("Please enter a valid e-mail address."); 
        email.focus(); 
        return false; 
    } 
   
    if (email.value.indexOf(".", 0) < 0)                 
    { 
        window.alert("Please enter a valid e-mail address."); 
        email.focus(); 
        return false; 
    } 
   
    if (count.value == "")                               
    { 
        window.alert("Please enter a valid input."); 
        count.focus(); 
        return false; 
    } 
    if(resume.value == ""){
        window.alert("Please upload a resume."); 
        resume.focus(); 
        return false;
    }
   
    return true; 
}

