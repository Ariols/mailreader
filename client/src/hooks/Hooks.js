import axios from 'axios';
import { useEffect, useState } from 'react';

export const useFetchData = (endpoint) => {
    const [data, setData] = useState({});
    const [loading, setLoading] = useState(false);
    // Mettre quelques chose de mieux
    const url = `http://localhost:8080/api/${endpoint}`;
    useEffect(() => {
        const fetchData = async () => {
            try {
                setData({});
                setLoading(true);
                const { data: response } = await axios.get(url);
                setData(response);
            } catch (error) {
                console.error(error);
            }
            setLoading(false);
        };
        fetchData();
    }, [endpoint]);

    return {
        data,
        loading,
    };
};

export default useFetchData;