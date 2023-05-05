import { AppBar, Box, Button, CircularProgress, Container, CssBaseline, Unstable_Grid2 as Grid, Paper, ThemeProvider, Toolbar, Tooltip, Typography, createTheme } from '@mui/material'
import { amber, deepOrange } from '@mui/material/colors'
import { GetServerSidePropsContext } from 'next'
import Head from 'next/head'
import Image from 'next/image'
import client from '../ts/ApiClient'
import { useState } from 'react'

interface HomeProps {
  cards: any[]

}

export default function Home({ }: HomeProps) {
  return (
    <>
      <Head>
        <title>Jackenate</title>
      </Head>
      <Grid container spacing={3} sx={{ mt: 1 }}>
        <Grid xs={12}>
          <Typography variant="h1" align="center">
            Hello World!
          </Typography>
        </Grid>
      </Grid>
    </>
  )
}