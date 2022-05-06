import * as React from 'react';
import Grid from '@mui/material/Grid';
import Typography from '@mui/material/Typography';
import Link from '@mui/material/Link';
import Container from '@mui/material/Container';

export function Copyright(props) {
    return (
        <Typography variant="body2" color="text.secondary" align="center" {...props}>
            {'Copyright © '}
            <Link color="inherit" href="https://mui.com/">
                DevOpsify
            </Link>{' '}
            {new Date().getFullYear()}
            {'.'}
        </Typography>
    );
}

const footers = [
    // {
    //     // title: 'Company',
    //     description: ['Team', 'History', 'Contact us', 'Locations'],
    // },
    // {
    //     description: [
    //         'Contact Us',
    //     ],
    // },
    // {
    //     title: 'Resources',
    //     description: ['Resource', 'Resource name', 'Another resource', 'Final resource'],
    // },
    // {
    //     title: 'Legal',
    //     description: ['Privacy policy', 'Terms of use'],
    // },
];

function PricingContent() {
    return (
        <React.Fragment>
            {/* Footer */}
            <Container
                maxWidth="md"
                component="footer"
                sx={{
                    borderTop: (theme) => `1px solid ${theme.palette.divider}`,
                    mt: 8,
                    py: [3, 6],
                }}
            >
                <Grid container spacing={4} justifyContent="space-evenly">
                    {footers.map((footer) => (
                        <Grid item xs={6} sm={3} key={footer.title}>
                            <Typography variant="h6" color="text.primary" gutterBottom>
                                {footer.title}
                            </Typography>
                            <ul>
                                {footer.description.map((item) => (
                                    <li key={item}>
                                        <Link href="#" variant="subtitle1" color="text.secondary">
                                            {item}
                                        </Link>
                                    </li>
                                ))}
                            </ul>
                        </Grid>
                    ))}
                </Grid>
                <Copyright sx={{mt: 5}}/>
            </Container>
        </React.Fragment>
    );
}

export default function Pricing() {
    return <PricingContent/>;
}