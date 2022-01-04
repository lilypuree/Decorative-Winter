package lilypuree.decorative_winter;

import lilypuree.decorative_winter.core.setup.BlockActivateEventHandler;
import net.minecraft.world.InteractionResult;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DWConstants.MODID)
public class EventHandler {

    @SubscribeEvent
    public static void onBlockActivate(PlayerInteractEvent.RightClickBlock event) {
        BlockActivateEventHandler handler = new BlockActivateEventHandler(event.getWorld(), event.getPos(), event.getPlayer(), event.getHand(), event.getItemStack());
        InteractionResult result = handler.getResult();
        if (result == InteractionResult.FAIL) {
            event.setUseItem(Event.Result.DENY);
        } else if (result == InteractionResult.SUCCESS) {
            event.setUseBlock(Event.Result.DENY);
            event.setUseItem(Event.Result.DENY);
        }
    }
}
