export const handleFormInputChange =(event,obj,setObj)=>{
    const {name,value} = event.target;
    setObj({...obj,[name]:value});
}

export const handleFormCheckBoxChange =(event,obj,setObj)=>{
    const {name,checked} = event.target;
    setObj({...obj,[name]:checked});
}
