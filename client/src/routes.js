
import Charts from "./components/Charts";
import Home from "./components/Home";

const ROUTES = [
    {
        title: 'Accueil',
        name: 'home',
        path: '/',
        element: <Home />
    },
    {
        title: 'Diagramme',
        name: 'charts',
        path: '/charts',
        element: <Charts />
    },
];

export default ROUTES;