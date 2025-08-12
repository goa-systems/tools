document.addEventListener('DOMContentLoaded', async () => {
    try {
        
        const response = await fetch('/index.json')

        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`)
        }

        const data = await response.json()

        const subtitle = document.getElementById('version')
        const navigation = document.getElementById('navigation')
        
        subtitle.textContent = data.version
        
        data.modules.forEach(element =>{
            let submenu = document.getElementById('submenu')
            let content = document.getElementById('content')
            let link = document.createElement("a")
            link.textContent = element.displayname
            link.href = "#"
            link.onclick = function (){
                submenu.textContent = element.subelements
                content.textContent = element.description
            }
            navigation.appendChild(link)
            if(element.default){
                submenu.textContent = element.subelements
                content.textContent = element.description
            }
        })

    } catch (error) {
        console.error('Error fetching data:', error);
    }
});
