import Box from '@mui/material/Box';
import FormControl from '@mui/material/FormControl';
import InputLabel from '@mui/material/InputLabel';
import LinearProgress from '@mui/material/LinearProgress';
import MenuItem from '@mui/material/MenuItem';
import Select from '@mui/material/Select';
import { ArcElement, Chart as ChartJS, Legend, Tooltip, RadialLinearScale } from 'chart.js';
import React, { useState } from 'react';
import { Pie, Doughnut, PolarArea } from 'react-chartjs-2';
import { useFetchData } from '../hooks/Hooks';
import './Charts.css';

ChartJS.register(RadialLinearScale, ArcElement, Tooltip, Legend);

const MAIL_IMPORTANCE = 'mail-importance';
const MAIL_COUNT = 'mail-count';
const MAIL_ATTACHEMENT = 'mail-attachements';
const CHART_TYPE = [
    {
        label: 'Importance',
        value: MAIL_IMPORTANCE,
    },
    {
        label: 'Nombre de mail',
        value: MAIL_COUNT,
    },
    {
        label: 'Pièces jointes',
        value: MAIL_ATTACHEMENT,
    },
];

const CHART_COLORS = [
    'rgba(255, 99, 132, 0.2)',
    'rgba(54, 162, 235, 0.2)',
    'rgba(255, 206, 86, 0.2)',
    'rgba(75, 192, 192, 0.2)',
    'rgba(153, 102, 255, 0.2)',
    'rgba(255, 159, 64, 0.2)',
];

const CHART_BORDER_COLORS = [
    'rgba(255, 99, 132, 1)',
    'rgba(54, 162, 235, 1)',
    'rgba(255, 206, 86, 1)',
    'rgba(75, 192, 192, 1)',
    'rgba(153, 102, 255, 1)',
    'rgba(255, 159, 64, 1)',
];

const buildChartData = (title, labels, values) => {
    return {
        labels: labels,
        datasets: [
            {
                label: title ?? 'Titre',
                data: values,
                backgroundColor: CHART_COLORS,
                borderColor: CHART_BORDER_COLORS,
                borderWidth: 1,
            },
        ],
    }
}

const Charts = () => {
    const [chartType, setChartType] = useState('mail-importance');
    const {
        data,
        loading,
    } = useFetchData(chartType);

    return loading ?
        (<div>
            <div>
                <Box sx={{ width: '100%' }}>
                    <LinearProgress />
                    Chargement en cours
                </Box>
            </div>
        </div>) : (
            <Box sx={{ width: '100%' }}>
                <div className='chartsContainer'>
                    <FormControl width="50%">
                        <InputLabel>Type</InputLabel>
                        <Select
                            labelId="chart-type"
                            id="chart-type-id"
                            value={chartType}
                            label="Type"
                            onChange={(event) => {
                                if (event.target.value !== chartType) {
                                    setChartType(event.target.value);
                                }
                            }}>
                            {CHART_TYPE.map(type => <MenuItem key={type.value} value={type.value}>{type.label}</MenuItem>)}
                        </Select>
                    </FormControl>
                    <div className='charts'>
                        <ChartSelector data={data} chartType={chartType} />
                    </div>
                </div>
            </Box>);
}

const ChartSelector = ({ data, chartType }) => {
    if (!data?.length) {
        return (<div> Aucune donnée </div>)
    }
    const labels = [];
    const values = [];
    for (const item of data) {
        if (item.label == null || item.value == null) {
            continue;
        }
        labels.push(item.label);
        values.push(item.value);
    }

    if (values.length === 0 || labels.length === 0) {
        return (<div> Aucune données exploitable pour faire un diagramme </div>)
    }

    switch (chartType) {
        case MAIL_IMPORTANCE:
            return <ImportanceChart labels={labels} values={values} />;
        case MAIL_COUNT:
            return <CountChart labels={labels} values={values} />;
        case MAIL_ATTACHEMENT:
            return <AttachementChart labels={labels} values={values} />;
        default:
            break;
    }
}

const ImportanceChart = ({ labels, values }) => {
    const chartData = buildChartData('Importance', labels, values);
    return (<Doughnut data={chartData} />);
}

const CountChart = ({ labels, values }) => {
    const chartData = buildChartData('Personnes ayant des mails', labels, values);
    return (<PolarArea data={chartData} />);
}

const AttachementChart = ({ labels, values }) => {
    const chartData = buildChartData('Pièces jointes', labels, values);
    return (<Pie data={chartData} />);
}

export default Charts;
