import AppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import React from 'react';
import { Route, Routes } from "react-router-dom";
import './App.css';
import Menu from "./components/Menu";
import ROUTES from './routes';

const App = () => {
  return (
    <div className="App">
      {/* <NavBar /> */}
      <Menu />
      <Routes>
        {ROUTES.map(route => {
          const { name, path, element } = route;
          return <Route key={name} path={path} element={element} />
        })}
      </Routes>
    </div>
  );
}

const NavBar = () => {
  return (
    <React.Fragment>
      <AppBar position="fixed">
        <Toolbar>
          Menu
        </Toolbar>
      </AppBar>
      <Toolbar />
    </React.Fragment>
  )
}

export default App;
