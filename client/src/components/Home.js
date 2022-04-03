import React from 'react';
import { Link } from "react-router-dom";

const Home = () => {
    return(<div>
        Home page
         <Link
            style={{ display: "block", margin: "1rem 0" }}
            to='/charts'>
            charts
        </Link>
    </div>)
}

export default Home;