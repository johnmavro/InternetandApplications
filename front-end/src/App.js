import React, {useState} from 'react';
import logo from './logo.svg';
import './App.css';

function App() {
  const [disease, setDisease] = useState()
  const [data, setData]  = useState({
    "AuthorList": [
        {
            "name": "",
            "number": ""
        }]})

  function handleSubmit(){
    const url = 'http://localhost:8080/internet/applications/Scientists/diseases'
    fetch(url, {
      method:'POST',
      headers:{
        'Content-Type':'application/x-www-form-urlencoded;charset=UTF-8'
      },
      body:new URLSearchParams({
        'disease':disease
      })
    })
    .then(response=>response.json())
    .then(res=>setData(res))
    .catch(e=>console.log(e))
  }


  const result = data.AuthorList.map((item)=>
    <div>
    {item.name?
      <div className='containerB'>
      <h4>{item.name}</h4>
      <h4 style={{marginLeft:'350px'}}>{item.number}</h4>
      </div>
      :null}
    </div>
  )


  return (
    <div className='containerA'>
      <div className='containerB'>
      <input type='text' value={disease} onChange={(e)=>setDisease(e.target.value)}placeholder="Disease"/>
      <button className='submit' type='submit' onClick={handleSubmit}>Submit</button>
      </div>
      {data.AuthorList.length>1?<div className='containerB'>
        <h2>Authors</h2>
        <h2 style={{marginLeft:'300px'}}>Numbers</h2>
        </div>:null}
      {data?result:null}
    </div>
  );
}

export default App;
